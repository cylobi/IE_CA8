import path from 'path';

export default {
    entry: './frontend/index.ts', // Your entry point
    output: {
        filename: 'bundle.js', // Output filename
        path: path.resolve(__dirname, 'dist'), // Output directory
    },
};