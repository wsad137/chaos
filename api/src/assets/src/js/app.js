var app = angular.module("apiApp", ['appServices','ui.bootstrap','angularFileUpload','toastr']);
// var app = angular.module("pmsApp", ['appServices', 'ui.bootstrap', 'ngAnimate', 'toastr', 'angularFileUpload']);
// var app = angular.module("pmsApp", ['appServices', 'ui.bootstrap', 'ngAnimate', 'toastr']);

app.controller("apiCtrl", apiCtrl);


// app
// config(['toastrConfig', function (toastrConfig) {/*消息提示框*/
//     angular.extend(toastrConfig, {
//         timeOut: '2500',
//         autoDismiss: false,
//         containerId: 'toast-container',
//         maxOpened: 0,
//         newestOnTop: true,
//         positionClass: 'toast-top-center',
//         preventDuplicates: false,
//         progressBar: false,
//         preventOpenDuplicates: false,
//         target: 'body'
//     });
//     // "closeButton": true,
//     // "progressBar": true,
//     // "positionClass": "toast-top-center",
//     // "showDuration": "300",
//     // "hideDuration": "1000",
//     // "timeOut": "2500",
//     // "extendedTimeOut": "1000",
//     // "showEasing": "swing",
//     // "hideEasing": "linear",
//     // "showMethod": "fadeIn",
//     // "hideMethod": "fadeOut"
// }])
app.config(['$httpProvider', function ($httpProvider) {/*启用跨域cookie*/
    // TODO Safari 浏览器不兼容
    $httpProvider.defaults.withCredentials = true;
}]);


var appServices = angular.module('appServices', []);
/**
 * 配置http
 */
appServices.service("http", http);


function http($http, toastr) {
    this.post = function (url, data, fn) {
        $http.post(url, data).then(function (resp) {
            //console.info("*-*-*-*-sDao-success");
            var data = resp.data;
            // if (!data.success) {
            //     toastr.warning(data.eMsg, "", {
            //         onHidden: function () {
            //             if (data.eCode === -1) window.location.href = "../index.html";
            //         }
            //     });
            //     return;
            // }
            console.info(url);
            console.info(data);
            // console.info(JSON.stringify(data));
            fn(data, resp.status, resp.headers, resp.config)
        }, function (resp) {
            // 响应失败时调用，resp带有错误信息
            console.error(url);
            console.error(resp.data);
            toastr.error("链接超时！");
            // fn(resp.data, resp.status, resp.headers, resp.config);
        });
    };
    this.get = function (url, data, fn) {
        $http.get(url, data).then(function (resp) {
            //console.info("*-*-*-*-sDao-success");
            var data = resp.data;
            // if (!data.success) {
            //     toastr.warning(data.eMsg, "", {
            //         onHidden: function () {
            //             if (data.eCode === -1) window.location.href = "../index.html";
            //         }
            //     });
            //     return;
            // }
            console.info(url);
            console.info(data);
            // console.info(JSON.stringify(data));
            fn(data, resp.status, resp.headers, resp.config)
        }, function (resp) {
            // 响应失败时调用，resp带有错误信息
            console.error(url);
            console.error(resp.data);
            toastr.error("链接超时！");
            // fn(resp.data, resp.status, resp.headers, resp.config);
        });
    };
    this._post = function (url, data, fn) {
        $http.post(url, data).then(function (resp) {
            //console.info("*-*-*-*-sDao-success");
            var data = resp.data;
            // console.info(new Date());
            // console.info(data.data);
            // console.info(JSON.stringify(data));
            console.info(url);
            console.info(data);
            fn(resp.data, resp.status, resp.headers, resp.config)
        }, function (resp) {
            // 响应失败时调用，resp带有错误信息
            console.error(url);
            console.error(resp.data);
            toastr.error("链接超时！");
            // fn(resp.data, resp.status, resp.headers, resp.config);
        });
    };
    /*
     * 通用方法
     */

    // /**
    //  * 获取名宿信息
    //  * @param call
    //  */
    // this.getHostel = function (call) {
    //     var param = {};
    //     // param.beginTime = new Date().getTime() - (3 * 24 * 3600);
    //     // param.endTime = param.beginTime + (30 * 24 * 3600);
    //     this.post(PATH.hostelList, param, function (d) {
    //         if (!d) return;
    //         call(d)
    //     })
    // };
    // /**
    //  * 获取城市信息
    //  * @param parentId
    //  * @param call
    //  */
    // this.getRegino = function (parentId, call) {
    //     this.post(PATH.getRegion, {"parentId": parentId}, function (d) {
    //         call(d);
    //     })
    // };
    // this.logout = function () {
    //     this.post(PATH.logout, {}, function () {
    //         toastr.success("已退出！", "", {
    //             timeOut: 250,
    //             onHidden: function () {
    //                 window.location.href = "../index.html";
    //             }
    //         });
    //         // location.href = "index.html"
    //     })
    // };
}