const myexpress = require('express');
const router = myexpress.Router();


router.get('/header.html',function(req,res){

        res.render('header')
})
router.get('/index.html',function(req,res){
    res.render('index')
})
router.get('/activity.html',function(req,res){
    res.render('activity')
})
router.get('/gallery.html',function(req,res){
        res.render('gallery')

})
router.get('/about.html',function(req,res){
    res.render('about')
})
router.get('/match.html',function(req,res){
    res.render('match')
})
router.get('/idea.html',function(req,res){
    res.render('idea')
})
router.get('/teaching.html',function(req,res){
    res.render('teaching')
})
router.get('/special.html',function(req,res){
    res.render('special')
})
router.get('/service.html',function(req,res){
    res.render('service')
})
router.get('/teacher.html',function(req,res){
    res.render('teacher')
})
router.get('/teaching_system.html',function(req,res){
    res.render('teaching_system')
})
router.get('/contact.html',function(req,res){
    res.render('contact')
})
router.get('/class.html',function(req,res){
    res.render('class')
})



router.get('/contact.html',function(req,res){
    res.render('contact')
})
router.get('/partner.html',function(req,res){
    res.render('partner')
})
router.get('/PayList_html.html',function(req,res){
    if(req.session.user){
        res.locals.session = req.session;
        user=req.session.user
        res.render('PayList_html')
    }
else{
    res.render('login')  
}
})
router.get('/productDetail.html',function(req,res){
    res.locals.session = req.session;
    user=req.session.user
    res.render('productDetail')
})
router.get('/SoftDownload.html',function(req,res){
    res.render('SoftDownload')
})
router.get('/PayWays_html.html',function(req,res){
    if(req.session.user){
        res.locals.session = req.session;
        user=req.session.user
        res.render('PayWays_html')
    }
else{
    res.render('login')  
}
})

exports.routes=router; //公开