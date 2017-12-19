/**
 * gulp配置文件,引入gulp及各种组件;
 */
var gulp = require('gulp'),
    // js 压缩插件 （用于压缩 JS）
    uglify = require('gulp-uglify'),
    // 压缩angularJs 文件
    ngmin = require('gulp-ngmin'),
    ngAnnotate = require('gulp-ng-annotate'),
    // js 去除console插件
    stripDebug = require('gulp-strip-debug'),
    // 压缩css插件
    minifyCSS = require('gulp-minify-css'),
    // 获取 gulp-imagemin 模块
    imagemin = require('gulp-imagemin'),
    // 压缩html插件
    htmlmin = require('gulp-htmlmin'),
    // 复制文件（文件拷贝）
    copy = require('copy'),
    //less和sass的编译
    sass = require("gulp-sass"),
    // 清除文件
    del = require('del'),
    //启动服务器
    browserSync = require('browser-sync').create(),
    //包管理工具
    bower = require('gulp-bower'),
    //替换内容
    replace = require('gulp-replace'),

    //接收控制台输入参数
    minimist = require('minimist')(process.argv.slice(2), {default: {'env': 'dev'}}),

    fileinclude = require('gulp-file-include'),
    /*合并文件*/
    concat = require('gulp-concat'),
    //打包压缩
    compressionZip = require('gulp-zip'),

    /*记录流路径*/
    vinylPaths = require('vinyl-paths'),
    /*重命名*/
    rev = require('gulp-rev'),

    /*使用重命名json-执行替换*/
    revCollector = require('gulp-rev-collector');

//gulp task执行顺序
var runSequence = require('run-sequence').use(gulp);
var fs = require('fs');


// gulp default
gulp.task('default', ['server']);


gulp.task("bower-update", function () {
    return bower({cmd: 'update', interactive: true});
});

gulp.task('bower-libs', ['bower-update'], function () {
    gulp.src('./bower_components/angular/angular.min.js').pipe(gulp.dest('./src/lib/angular/'));
    gulp.src('./bower_components/angular-cookies/angular-cookies.min.js').pipe(gulp.dest('./src/lib/angular/'));
    gulp.src('./bower_components/angular-sanitize/angular-sanitize.min.js').pipe(gulp.dest('./src/lib/angular/'));
    gulp.src('./bower_components/angular-ui-router/release/angular-ui-router.min.js').pipe(gulp.dest('./src/lib/angular/'));
    gulp.src('./bower_components/angular-file-upload/dist/*').pipe(gulp.dest('./src/lib/angular-file-upload/dist/'));
    gulp.src('./bower_components/angular-bootstrap/ui-bootstrap-tpls.js').pipe(gulp.dest('./src/lib/angular-bootstrap/'));
    gulp.src(['./bower_components/angular-toastr/dist/angular-toastr.tpls.js', './bower_components/angular-toastr/dist/angular-toastr.css']).pipe(gulp.dest('./src/lib/angular-toastr/'));
    gulp.src('./bower_components/angular-animate/angular-animate.min.js').pipe(gulp.dest('./src/lib/angular/'));

    gulp.src('./bower_components/bootstrap/dist/**').pipe(gulp.dest('./src/lib/bootstrap/'));
    gulp.src('./bower_components/bootstrap-table/dist/**').pipe(gulp.dest('./src/lib/bootstrap-table/'));
    gulp.src('./bower_components/bootstrap-editable/src/**').pipe(gulp.dest('./src/lib/bootstrap-editable/'));
    gulp.src(['./bower_components/bootstrap-sweetalert/dist/sweetalert.css', './bower_components/bootstrap-sweetalert/dist/sweetalert.min.js']).pipe(gulp.dest('./src/lib/bootstrap-sweetalert/'));
    gulp.src('./bower_components/jquery/dist/jquery.min.js').pipe(gulp.dest('./src/lib/jquery/'));
    gulp.src('./bower_components/jquery-backstretch/jquery.backstretch.min.js').pipe(gulp.dest('./src/lib/jquery-backstretch/'));
    gulp.src('./bower_components/jquery-validation/dist/*.min.*').pipe(gulp.dest('./src/lib/jquery-validation/'));
    gulp.src('./bower_components/jquery-validation/src/localization/messages_zh.js').pipe(gulp.dest('./src/lib/jquery-validation/localization/'));
    gulp.src('./bower_components/font-awesome/css/font-awesome.min.css').pipe(gulp.dest('./src/lib/font-awesome/css/'));
    gulp.src('./bower_components/font-awesome/fonts/**').pipe(gulp.dest('./src/lib/font-awesome/fonts/'));
    gulp.src('./bower_components/jsoneditor/dist/jsoneditor-minimalist.min.js').pipe(gulp.dest('./src/lib/jsoneditor/dist/'));
    gulp.src('./bower_components/jsoneditor/dist/jsoneditor.css').pipe(gulp.dest('./src/lib/jsoneditor/dist/'));
    gulp.src('./bower_components/jsoneditor/dist/img/**').pipe(gulp.dest('./src/lib/jsoneditor/dist/img/'));
});
gulp.task('clean', function () {
    return del(['dist/**', 'rev/**'])
});
gulp.task('build', function (cb) {
    // var env2 = minimist.env;
    // var vp0 = vinylPaths();
    // return gulp.src(['dist/' + env2 + '/js/*.*', 'dist/' + env2 + '/css/*.*']).pipe(vp0).on('end', function () {
    //     del.sync(vp0.paths, {force: true});
    //     gulp.src(['src/**', '!src/include/*.html', '!src/**/*.scss', '!src/**/*.map']).pipe(gulp.dest('dist/' + env2 + '/')).on('end', function () {
    //         gulp.src(['src/**/*.html', '!src/include/*.html']).pipe(fileinclude()).pipe(gulp.dest('dist/' + env2 + '/')).on('end', function () {
    //             gulp.src('src/**/*.scss').pipe(sass()).pipe(gulp.dest('dist/' + env2 + '/')).on('end', function () {
    //                 gulp.src(["env/" + env2 + ".js", 'src/js/app.js']).pipe(concat('js/app.js')).pipe(gulp.dest('dist/' + env2 + '/')).on('end', function () {
    //                     var vp = vinylPaths();
    //                     gulp.src(['dist/' + env2 + '/js/*.*']).pipe(vp).pipe(rev()).pipe(gulp.dest('dist/' + env2 + '/js/')).pipe(rev.manifest()).pipe(gulp.dest('rev/js')).on('end', function () {
    //                         del(vp.paths);
    //                         var vp2 = vinylPaths();
    //                         gulp.src(['dist/' + env2 + '/css/*.*']).pipe(vp2).pipe(rev()).pipe(gulp.dest('dist/' + env2 + '/css/')).pipe(rev.manifest()).pipe(gulp.dest('rev/css')).on('end', function () {
    //                             del(vp2.paths);
    //                             gulp.src(['rev/**/*.json', 'dist/' + env2 + '/**/*.html']).pipe(revCollector()).pipe(gulp.dest('dist/' + env2));
    //                         });
    //                     });
    //                 });
    //             });
    //         });
    //     });
    // });

    var env2 = minimist.env;
    var vp0 = vinylPaths();
    gulp.src(['dist/' + env2 + '/js/*.*', 'dist/' + env2 + '/css/*.*']).pipe(vp0).pipe(gulp.dest('dist/' + env2 + '/tmp')).on('end', function () {
        del(vp0.paths);
        del('dist/' + env2 + '/tmp');
        gulp.src(['src/**', '!src/include/*.html', '!src/**/*.scss', '!src/**/*.map','!**/*___jb_tmp___']).pipe(gulp.dest('dist/' + env2 + '/')).on('end', function () {
            gulp.src(['src/**/*.html', '!src/include/*.html']).pipe(fileinclude()).pipe(gulp.dest('dist/' + env2 + '/')).on('end', function () {
                gulp.src('src/**/*.scss').pipe(sass()).pipe(gulp.dest('dist/' + env2 + '/')).on('end', function () {
                    gulp.src(["env/" + env2 + ".js", 'src/js/app.js']).pipe(concat('js/app.js')).pipe(gulp.dest('dist/' + env2 + '/')).on('end', function () {
                        var vp = vinylPaths();
                        gulp.src(['dist/' + env2 + '/js/*.*']).pipe(vp).pipe(rev()).pipe(gulp.dest('dist/' + env2 + '/js/')).pipe(rev.manifest()).pipe(gulp.dest('rev/js')).on('end', function () {
                            del(vp.paths);
                            var vp2 = vinylPaths();
                            gulp.src(['dist/' + env2 + '/css/*.*']).pipe(vp2).pipe(rev()).pipe(gulp.dest('dist/' + env2 + '/css/')).pipe(rev.manifest()).pipe(gulp.dest('rev/css')).on('end', function () {
                                del(vp2.paths);
                                gulp.src(['rev/**/*.json', 'dist/' + env2 + '/**/*.html']).pipe(revCollector()).pipe(gulp.dest('dist/' + env2)).on('end', function () {
                                    cb();
                                });
                            });
                        });
                    });
                });
            });
        });
    });
});
gulp.task('server', function () {
    var env2 = minimist.env;
    browserSync.init({
        server: 'dist/' + env2 + '/'
    });
    // gulp.watch('src/**', ['build']).on('change', browserSync.reload);
    // gulp.watch('src/**', ['build']).on('change', browserSync.reload);

    gulp.watch(['src/**', '!**/*___jb_tmp___'], function () {
        gulp.task('reload', function () {
            return browserSync.reload();
        });
        runSequence('build', 'reload');
    });
});


// 线上打包
gulp.task('package', function () {
    runSequence('clean', 'build', 'zip');
});
// 线上打包
gulp.task('package-lib', function () {
    runSequence('clean', 'bower-libs', 'build', 'zip');
});
gulp.task('zip', function () {
    var env2 = minimist.env;
    return gulp.src('dist/' + env2 + '/' + "**/*.*")
        .pipe(compressionZip('chaosApi.zip'))
        // .pipe(compressionZip(env2 + '.zip'))
        .pipe(gulp.dest('dist/'));
    // return runSequence('clean', 'bower-libs', 'build', 'zip');
});

/**
 * 参数说明
 * --env dev 默认开发环境
 * --env test 测试环境
 * --env production 生产环境
 *
 */