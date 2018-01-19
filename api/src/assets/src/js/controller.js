/**
 * Created by chaos on 2017/6/9.
 */


/*OPTIONS、GET、HEAD、POST、PUT、DELETE、TRACE、CONNECT*/
var type = ['POST', 'GET', 'PUT', 'DELETE'];
/*Content-Type*/
var cType = ['application/json', 'application/x-www-form-urlencoded', 'multipart/form-data', 'text/xml'];


/*JSONEditor option*/
var options = {
    mode: 'view',
    modes: ['text', 'view', 'code', 'form', 'tree'] // allowed modes
//        modes: ['code', 'form', 'text', 'tree', 'view'] // allowed modes
};

/**
 *
 * 控制器
 * @param $scope
 */
function apiCtrl($scope, http, toastr) {
    $scope.types = type;
    $scope.cTypes = cType;

    $scope.global = {isShow: false, cType: 'application/json', type: 'POST'};
    $scope.global.basePath = location.protocol + "//" + location.host;
    $scope.header = {isShow: false};
    $scope.header.childs = [{}];

    $scope.cookie = {isShow: false};
    $scope.cookie.childs = [{}];


    $scope.data = [];
    $scope.menu = [{isShow: true}, {isShow: true}];
    $scope.apis = [];

    /*请求记录*/
    $scope.record = {isShow: false, childs: []};

    /*还原本地存储的全局设置 配置*/
    if (getItem('global')) $scope.global = JSON.parse(getItem('global'));
    if (getItem('header')) $scope.header = JSON.parse(getItem('header'));
    if (getItem('cookie')) $scope.cookie = JSON.parse(getItem('cookie'));
    if (getItem('record')) $scope.record = JSON.parse(getItem('record'));

    /*清除默认配置*/
    $scope.globalDef = function () {
        localStorage.clear();
        location.reload()
    };
    /*添加header、cookie头*/
    $scope.add = function (list) {
        list.push({})
    };
    /*删除header、cookie头*/
    $scope.del = function (list, item) {
        list.remove(item)
    };

    $scope.apiChange = function (idGroup, idApi) {
        /*菜单显示*/
        $scope.menu.forEach(function (i) {
            i.isShow = false;
            if (i.id === idGroup) i.isShow = true;

        });
        /*表单显示*/
        if (!idApi) return;
        $scope.apis.forEach(function (i) {
            i.isShow = false;
            if (i.id === idApi) i.isShow = true;
        })


    };

    /*加载数据*/
    http.get("./data.json", {}, function (d) {
        if (!d) d = [];
        $scope.data.pushAll(d);
        $scope.menu.clear();
        $scope.menu.pushAll(d);

        d.forEach(function (i) {
            $scope.apis.pushAll(i.apis);
            $scope.apis.forEach(function (t) {
                t.type = $scope.global.type;
                t.cType = $scope.global.cType;
            })
        });

        /*url 来源入口*/
        var hash = location.hash;
        if (hash && hash.length > 1) {
            var names = hash.substring(1).split("/");
            var group = $scope.menu.find('name', names[0]);
            if (!group) return;
            var api = group.apis.find("name", names[1]);
            if (api) $scope.apiChange(api.idGroup, api.id)

        }
    });

    /*发送请求*/
    $scope.submit = function (api) {
        /*添加请求记录*/
        $scope.record.childs.push({idGroup: api.idGroup, id: api.id, name: api.name, nameGroup: api.nameGroup, time: new Date().getTime()});
        if ($scope.record.childs.length > 50) $scope.record.childs.shift();

        if (!api.JSONEditor) {
            api.JSONEditor = new JSONEditor(angular.element("#show-" + api.id)[0], options)
        }

        if (api.type === 'POST') {
            http.post($scope.global.basePath + api.url, api.data, function (d) {
                api.JSONEditor.set(d);
            })
        } else if (api.type === 'GET') {

        }
        // TODO 其他提交方式待续
        /*其他待续...*/
    };


    /*动态保存全局设置*/
    $scope.$watch("global", function (value) {
        setItem('global', JSON.stringify(value));
    }, true);
    $scope.$watch("header", function (value) {
        setItem('header', JSON.stringify(value));
    }, true);
    $scope.$watch("cookie", function (value) {
        setItem('cookie', JSON.stringify(value));
    }, true);
    $scope.$watch("record", function (value) {
        setItem('record', JSON.stringify(value));
    }, true);

}
