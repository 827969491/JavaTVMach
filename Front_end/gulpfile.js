var gulp = require('gulp');
var concat = require('gulp-concat');
//- 多个文件合并为一个；

var minifyCss = require('gulp-minify-css');
//- 压缩CSS为一行； 

var rev = require('gulp-rev');
//- 对文件名加MD5后缀

var revCollector = require('gulp-rev-collector');
//- 路径替换
var  autoprefixer = require('gulp-autoprefixer');
// -添加前缀
var gulp=require('gulp'),  //gulp基础库

// var useref=require('gulp-useref'); 
 //自动替换html的css路径
 
    minifycss=require('gulp-minify-css'),   //css压缩
    concat=require('gulp-concat'),   //合并文件
    uglify=require('gulp-uglify'),   //js压缩
    rename=require('gulp-rename'),   //文件重命名
    notify=require('gulp-notify');   //提示

gulp.task('default',function(){
  console.log('hello world');
});
gulp.src('public/*.html')         // 获取流的api
    .pipe(gulp.dest('views/*.html')); 

    gulp.task('html', function () {
      return gulp.src(('public/*.html')) /*后面本地html文件的路径，可自行配置*/

        .pipe(gulp.dest('views/')); /*Html更换css、js文件版本,和本地html文件的路径一致*/
});

gulp.task('min', function() {                                //- 创建一个名为 min 的 task
  gulp.src(['public/css/*.css'])    //- 需要处理的css文件，放到一个字符串数组里
      .pipe(concat('my.min.css'))                            //- 合并后的文件名
      .pipe(minifyCss())                                     //- 压缩处理成一行
      .pipe(rev())                                            //- 文件名加MD5后缀
      .pipe(gulp.dest('views/css'))                               //- 输出文件本地                            
});

gulp.task('rev', function() {
  gulp.src(['router/*.json', 'public/index.html'])   //- 读取 rev-manifest.json 文件以及需要进行css名替换的文件
      .pipe(revCollector())                                   //- 执行文件内css名的替换
      .pipe(gulp.dest('views/css'));                     //- 替换后的文件输出的目录
});



gulp.task('minifycss',function(){
  return gulp.src(['public/css/*.css','!public/css/animate.css','!public/css/swiper.min.css','!public/css/header.css','!public/css/footer.css'])      //设置css
      // .pipe(concat('my.css'))  //合并css文件到"order_query"
        .pipe(autoprefixer({
          browsers: ['last 5 versions', 'Android >= 4.0'],
          add:true,
          cascade: true, //是否美化属性值 默认：true 
          remove:true, //是否去掉不必要的前缀 默认：true 
          flexbox: true     
          }))  
      .pipe(gulp.dest('public/css'))           //设置输出路径
      // .pipe(rename({suffix:'.min'}))         //修改文件名
      // .pipe(minifycss())                    //压缩文件
      // .pipe(gulp.dest('public/static'))            //输出文件目录
      .pipe(notify({message:'css task ok'}));   //提示成功
});



