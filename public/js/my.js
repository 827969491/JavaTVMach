
// 初始化dom设置
~~function setSize(){
    window.onresize = function () { setSize();};
    h = window.innerHeight;
    w = window.innerWidth;
    $(".banner").css({ 'height': h });
    $(".mask").css({ 'height': h });

}()

var clipPath = function (t) {
    if (!t) {
      return false
    }
    t.removeAttribute("id");
    var r = {
      height: t.clientHeight,
      width: t.clientWidth,
      distance: 30,
      html: t.outerHTML
    };
    if (window.getComputedStyle(document.body).webkitClipPath) {
      var a = r.distance,
        n = r.width,
        e = r.height;
      var o = "";
      for (var i = 0; i < n; i += a) {
        for (var h = 0; h < e; h += a) {
          var d = [i, h],
            u = [i, h + a],
            l = [i + a, h + a],
            v = [i + a, h];
          var c = [i + a / 2, h + a / 2];
          var m = [
            [d, c, v],
            [d, u, c],
            [c, u, l],
            [v, c, l]
          ];
          m.forEach(function (t, a) {
            var n = t.map(function (t) {
              return t.map(function (t) {
                return t + "px"
              }).join(" ")
            }).join();

            var e = "-webkit-clip-path: polygon(" + n + ");";
            var i = Math.random();
            var h = i < .5 ? -1 : 1;
            var u = [600 * (.5 - Math.random()), 600 * (.5 - Math.random())];
            var l = "translate(" + u.map(function (t) {
              return t + "px"
            }).join() + ") rotate(" + Math.round(h * 360 * Math.random()) + "deg)";
            var v = "-webkit-transform:" + l + ";transform:" + l + ";";
            o = o + r.html.replace('">', '" style="' + e + v + '">')
          })
        }
      }
      t.parentNode.innerHTML = r.html + o;
      return true
    } else {
      t.className += " no-clipath";
      return false
    }
  };



  class slider{
    constructor(obj){
        // 定义初始值
        this.timer = obj.timer || 5000;
        this.box = obj.box
        this.w = window.innerWidth;
        this.i = 0
        this.len =$(`${this.box}`).children().length;
        this.bullet = obj.bullet
        this._auto();
        this._init();
        this._click()
    }
    // 自动轮播
    _auto(){
        let that = this
        setInterval(()=>{
            this.i++
            if(this.i>=this.len){
                this.i = 0
                }
                this._move()
        },this.timer)
    }
    // 容器宽度设置
    _init (){
        $(`${this.box}`).css({'width':this.w*this.len})
        $(`${this.box} div`).css({'width':this.w})
        for(var i=0;i<this.len;i++){
            $(`${this.bullet}`).append("<span></span>")
        }
        $(`${this.bullet} span`).eq(0).addClass("span-active")
    }
    // move方法
    _move(){
        $(`${this.box}`).css({'margin-left':-this.w*this.i})
        $(`${this.box} div`).removeClass("active")
        $(`${this.box} div`).eq(this.i).addClass("active");
        $(`${this.bullet} span`).removeClass("span-active")
        $(`${this.bullet} span`).eq(this.i).addClass("span-active")
    }
    // 点击bullet
    _click(){
       let that = this
        $(`${this.bullet}`).on('click','span',function(){
            that.i = $(this).index()
            that._move()
        })
    }
  }

