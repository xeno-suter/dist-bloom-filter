const { src } = require('gulp');
const shell = require('gulp-shell');

function buildLatex() {
    return src('./doc/*.tex', { read: false })
        .pipe(shell([
            'pdflatex -output-directory=./doc <%= file.path %>',
            'pdflatex -output-directory=./doc <%= file.path %>',
            'rm ./doc/*.aux ./doc/*.log ./doc/*.toc'
        ]));
}

exports.buildLatex = buildLatex;
