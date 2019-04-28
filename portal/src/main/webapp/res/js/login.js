var _uk = 'portal.login.un', _pk = 'portal.login.up' ,_ck = 'portal.cert.up',_sk = 'portal.cert.un';
$("#fzsyh").submit(function () {
    var b = $("#rm")[0].checked;
    localStorage.setItem(_uk, b ? $("#username").val() : '');
    localStorage.setItem(_pk, b ? $("#password").val() : '');
    return true;
});

$("#zsyh").submit(function () {
    var b = $("#ck1")[0].checked;
    localStorage.setItem(_ck, b ? $("#UserPwd").val() : '');
    localStorage.setItem(_sk, b ? $("#UserList").val() : '');
    return true;
});

$(document).ready(function(){
    addbg();
    var u = localStorage.getItem(_uk),p = localStorage.getItem(_pk),c = localStorage.getItem(_ck),s = localStorage.getItem(_sk);
    if(u && p){
        $("#username").val(u);
        $("#password").val(p);
    }
    if(c && s){
        $("#UserPwd").val(c);
        $("#UserList").val(s);
    }
    $("#rs").on('change',function(){
        if(this.checked){
            document.getElementById("fzsyh").reset();
            this.checked = false;
        }
    });
    $("#ck2").on('change',function(){
        if(this.checked){
            document.getElementById("zsyh").reset();
            this.checked = false;
        }
    });
});
function addbg(){
    //$("#content").css("margin","0 auto").width($(window).width()*0.3);
    $("#container").prepend('<div id="bgcontainer" style="position:absolute;top:0;width:100%;height:100%;"></div>');//z-index:-10;
    particle("bgcontainer");
}
function particle(id){
    particlesJS(id, {
        "particles": {
            "number": {
                "value": 40,
                "density": {
                    "enable": true,
                    "value_area": 800
                }
            },
            "color": {
                "value": "#ffffff"
            },
            "shape": {
                "type": "circle",
                "stroke": {
                    "width": 0,
                    "color": "#000000"
                },
                "polygon": {
                    "nb_sides": 5
                },
                "image": {
                    "src": "img/github.svg",
                    "width": 100,
                    "height": 100
                }
            },
            "opacity": {
                "value": 0.5,
                "random": false,
                "anim": {
                    "enable": false,
                    "speed": 1,
                    "opacity_min": 0.1,
                    "sync": false
                }
            },
            "size": {
                "value": 7,
                "random": true,
                "anim": {
                    "enable": false,
                    "speed": 40,
                    "size_min": 0.1,
                    "sync": false
                }
            },
            "line_linked": {
                "enable": true,
                "distance": 150,
                "color": "#ffffff",
                "opacity": 0.4,
                "width": 1.5
            },
            "move": {
                "enable": true,
                "speed": 1,
                "direction": "none",
                "random": false,
                "straight": false,
                "out_mode": "out",
                "bounce": false,
                "attract": {
                    "enable": false,
                    "rotateX": 600,
                    "rotateY": 1200
                }
            }
        },
        "interactivity": {
            "detect_on": "canvas",
            "events": {
                "onhover": {
                    "enable": false,
                    "mode": "grab"
                },
                "onclick": {
                    "enable": false,
                    "mode": "push"
                },
                "resize": true
            },
            "modes": {
                "grab": {
                    "distance": 140,
                    "line_linked": {
                        "opacity": 1
                    }
                },
                "bubble": {
                    "distance": 400,
                    "size": 40,
                    "duration": 2,
                    "opacity": 8,
                    "speed": 3
                },
                "repulse": {
                    "distance": 200,
                    "duration": 0.4
                },
                "push": {
                    "particles_nb": 4
                },
                "remove": {
                    "particles_nb": 2
                }
            }
        },
        "retina_detect": true
    });
}