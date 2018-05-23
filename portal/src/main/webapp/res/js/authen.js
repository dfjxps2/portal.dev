/*!
 * Start Bootstrap - SB Admin 2 v3.3.7+1 (http://startbootstrap.com/template-overviews/sb-admin-2)
 * Copyright 2013-2016 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap/blob/gh-pages/LICENSE)
 */
$(function() {
    $('#side-menu').metisMenu();
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
/*$(function() {
    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function() {
        return this.href == url;
    }).addClass('active').parent();

    var element2 = $('ul.pagination a').filter(function() {return this.href == url;}).addClass('active');

    while (true) {
        if (element.is('li')) {
            element = element.parent().addClass('in').parent();
        } else {
            break;
        }
    }
});*/
/*(function (window) {
    window.sys = {};

    window.sys.msg = function(Msg,obj,sfn){
        window.parent.layer.msg(Msg,obj,function(){sfn()});
    };

    window.sys.open = function(obj){
        window.parent.layer.open(obj);
    };
    window.sys.close = function(index){
        window.parent.layer.close(index);
    };

    window.sys.alertT = function (msg) {
        window.parent.layer.alert(msg);
    };
    window.sys.confirmT = function (msg, yesCallback,indexs,msgs) {
        window.parent.layer.confirm(msg, { icon:indexs,title: msgs}, function () {
            yesCallback();
            window.parent.layer.closeAll();
        }, function (index) {
            window.parent.layer.close(index);
        });
    };
})(window);*/
