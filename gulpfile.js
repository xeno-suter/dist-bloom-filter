const { src } = require('gulp');
const shell = require('gulp-shell');

function buildLatex() {
    return src('*.tex', { read: false }) // Modify the path as needed
        .pipe(shell(['pdflatex -interaction=nonstopmode <%= file.path %>']));
}

exports.build = buildLatex;
