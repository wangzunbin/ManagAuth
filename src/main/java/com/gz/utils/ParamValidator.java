/*
	MIT License http://www.opensource.org/licenses/mit-license.php
	Author Tobias Koppers @sokra
*/
"use strict";
let nextIdent = 0;

class CommonsChunkPlugin {
	constructor(options) {
		if(arguments.length > 1) {
			throw new Error(`Deprecation notice: CommonsChunkPlugin now only takes a single argument. Either an options
object *or* the name of the chunk.
Example: if your old code looked like this:
	new webpack.optimize.CommonsChunkPlugin('vendor', 'vendor.bundle.js')
You would change it to:
	new webpack.optimize.CommonsChunkPlugin({ name: 'vendor', filename: 'vendor.bundle.js' })
The available options are:
	name: string
	names: string[]
	filename: string
	minChunks: number
	chunks: string[]
	children: boolean
	async: boolean
	minSize: number`);
		}

		const normalizedOptions = this.normalizeOptions(options);

		this.chunkNames = normalizedOptions.chunkNames;
		this.filenameTemplate = normalizedOptions.filenameTemplate;
		this.minChunks = normalizedOptions.minChunks;
		this.selectedChunks = normalizedOptions.selectedChunks;
		this.children = normalizedOptions.children;
		this.deepChildren = normalizedOptions.deepChildren;
		this.async = normalizedOptions.async;
		this.minSize = normalizedOptions.minSize;
		this.ident = __filename + (nextIdent++);
	}

	normalizeOptions(options) {
		if(Array.isArray(options)) {
			return {
				chunkNames: options,
			};
		}

		if(typeof options === "string") {
			return {
				chunkNames: [options],
			};
		}

		// options.children and options.chunk may not be used together
		if(options.children && options.chunks) {
			throw new Error("You can't and it does not make any sense to use \"children\" and \"chunk\" options together.");
		}

		/**
		 * options.async and options.filename are also not possible together
		 * as filename specifies how the chunk is called but "async" implies
		 * that webpack will take care of loading this file.
		 */
		if(options.async && options.filename) {
			throw new Error(`You can not specify a filename if you use the "async" option.
You can however specify the name of the async chunk by passing the desired string as the "async" option.`);
		}

		/**
		 * Make sure this is either an array or undefined.
		 * "name" can be a string and
		 * "names" a string or an array
		 */
		const chunkNames = options.name || options.names ? [].concat(options.name || options.names) : undefined;
		return {
			chunkNames: chunkNames,
			filenameTemplate: options.filename,
			minChunks: options.minChunks,
			selectedChunks: options.chunks,
			children: options.children,
			deepChildren: options.deepChildren,
			async: options.async,
			minSize: options.minSize
		};
	}

	apply(compiler) {
		compiler.plugin("this-compilation", (compilation) => {
			compilation.plugin(["optimize-chunks", "optimize-extracted-chunks"], (chunks) => {
				// only optimize once
				if(compilation[this.ident]) return;
				compilation[this.ident] = true;

				/**
				 * Creates a list of "common"" chunks based on the options.
				 * The list is made up of preexisting or newly created chunks.
				 * - If chunk has the name as specified in the chunkNames it is put in the list
				 * - If no chunk with the name as given in chunkNames exists a new chunk is created and added to the list
				 *
				 * These chunks are the "targets" for extracted modules.
				 */
				const targetChunks = th