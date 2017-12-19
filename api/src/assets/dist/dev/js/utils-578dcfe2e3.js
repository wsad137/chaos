/**
 * 是否在微信内置浏览器
 * @returns {boolean}
 */
function isWeiXin() {

    // 使用微信 JS 对象判断是否微信内置浏览器
    // 建议使用此方法进行判断
    if (typeof WeixinJSBridge !== "undefined") return true;

    // 使用 userAgent 判断是否微信内置浏览器
    // 不推荐使用 用户可能会自行修改浏览器的 userAgent
    if (navigator.userAgent.toLowerCase().indexOf('micromessenger') > -1 || typeof navigator.wxuserAgent !== "undefined") return true;
    return false;
}


/**
 * 取url 参数
 * @param name
 * @returns {undefined}
 */
function urlParams(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r !== undefined && r !== null) return decodeURI(r[2]);
    // if (r != null) return unescape(r[2]);
    return undefined;
}

function setItem(k, v) {
    if (!k) return;
    if (!v) return;
    localStorage.setItem(k, v)
}

function getItem(k, def) {
    var t = localStorage.getItem(k);
    if (!t || t === "null" || t === null || t === "") return def;
    return t;
}


/*克隆*/
// Object.prototype.clone = function () {
//     var copy = (this instanceof Array) ? [] : {};
//     for (attr in this) {
//         if (!obj.hasOwnProperty(attr)) continue;
//         copy[attr] = (typeof this[i] === "object") ? obj[attr].clone() : obj[attr];
//     }
//     return copy;
// };

function clone(obj) {
    if (typeof(obj) === 'object') {
        var result = obj instanceof Array ? [] : {};
        for (var i in obj) {
            var attr = obj[i];
            result[i] = arguments.callee(attr);
        }
        return result;
    } else {
        return obj;
    }
}

/**
 *
 * @param v
 * @returns {*}
 */
function toStr(v) {
    var def = "";
    if (v === undefined) return def;
    if (v === null) return def;
    if (v === "") return def;
    if (v.length() === 0) return def;
    if (v === "null") return def;

    return v + "";

}

// 参数	默认值	描述
// title	null(required)	窗口的名称。可以通过对象的”title”属性或第一个参数进行传递。
// text	null	窗口的描述。可以通过对象的”text”属性或第二个参数进行传递。
// type	null	窗口的类型。SweetAlert 有4种类型的图标动画：”warning”, “error”, “success” 和 “info”.可以将它放在”type”数组或通过第三个参数传递。
// allowOutsideClick	false	如果设置为“true”，用户可以通过点击警告框以外的区域关闭警告框。
// showCancelButton	false	如果设置为“true”，“cancel”按钮将显示，点击可以关闭警告框。
// confirmButtonText	“OK”	该参数用来改变确认按钮上的文字。如果设置为”true”，那么确认按钮将自动将”Confirm”替换为”OK”。
// confirmButtonColor	“#AEDEF4”	该参数用来改变确认按钮的背景颜色（必须是一个HEX值）。
// cancelButtonText	“Cancel”	该参数用来改变取消按钮的文字。
// closeOnConfirm	true	如果希望以后点击了确认按钮后模态窗口仍然保留就设置为”false”。该参数在其他SweetAlert触发确认按钮事件时十分有用。
// imageUrl	null	添加自定义图片到警告框上。必须是图片的完整路径。
// imageSize	“80×80”	当设定图片的路径后，你可以设定图片的大小，格式为两个数字中间带个”x”符号。
// timer	null	警告框自动关闭的时间。单位是ms。


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
/**
 * 获取1970 到当前时间 的天数
 * @param date
 * @returns {number}
 */
var baseDate = new Date();
baseDate.setYear(1970);
baseDate.setMonth(1);
baseDate.setDate(1);
Date.prototype.getDays = function () {
    this.setHours(0);
    this.setMinutes(0);
    this.setSeconds(0);
    var days = parseInt((this.getTime() - baseDate.getTime()) / (1000 * 60 * 60 * 24));
    return days;
};

String.prototype.stringToDate = function () {
    //= ""
    var v = this;
    var index = v.lastIndexOf(".");
    if (index > 0) {
        v = v.substring(0, index)
    }
    return new Date(Date.parse(v.replace(/-/g, "/")));
    //return new Date(Date.parse(this.replace(/-/g, "/")));
};

Array.prototype.first = function () {
    if (this.length === 0) return undefined;
    return this[0];
};
Array.prototype.last = function () {
    var length = this.length;
    if (length === 0) return undefined;
    return this[length - 1];
};
Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] === val) return i;
    }
    return -1;
};

Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
Array.prototype.notEmpty = function () {
    return this.filter(function (t) {
        return t;
    });
};
/*
 * 方法:Array.Clear()
 * 功能:消空数组元素.
 * 参数:无.
 * 返回:空数组
 */
Array.prototype.clear = function () {
    // this.length = 0;
    return this.splice(0, this.length);
};

Array.prototype.pushAll = function (items) {
    // this.length = 0;
    for (var i = 0; i < items.length; i++) {
        this.push(items[i]);
    }
    // items.forEach(function (i) {
    //     super.push(i)
    // });
    // return this.splice(0, this.length);
};
Array.prototype.contains = function (key, val) {
    if (this.length === 0) return false;
    for (var i = 0; i < this.length; i++) {
        if (this[i][key] === val) return true;
    }
    return false;
};
Array.prototype.find = function (key, val) {
    if (this.length === 0) return false;
    for (var i = 0; i < this.length; i++) {
        if (this[i][key] === val) return this[i];
    }
    return undefined;
};


/* Method of Array*/
Array.prototype.clone = function () {
    var thisArr = this.valueOf();
    var newArr = [];
    for (var i = 0; i < thisArr.length; i++) {
        newArr.push(thisArr[i].clone());
    }
    return newArr;
};
/* Method of Boolean, Number, String*/
Boolean.prototype.clone = function () {
    return this.valueOf();
};
Number.prototype.clone = function () {
    return this.valueOf();
};
String.prototype.clone = function () {
    return this.valueOf();
};
/* Method of Date*/
Date.prototype.clone = function () {
    return new Date(this.valueOf());
};
/* Method of RegExp*/
RegExp.prototype.clone = function () {
    var pattern = this.valueOf();
    var flags = '';
    flags += pattern.global ? 'g' : '';
    flags += pattern.ignoreCase ? 'i' : '';
    flags += pattern.multiline ? 'm' : '';
    return new RegExp(pattern.source, flags);
};

//一维数组的排序
// type 参数
// 0 字母顺序（默认）
// 1 大小 比较适合数字数组排序
// 2 拼音 适合中文数组
// 3 乱序 有些时候要故意打乱顺序，呵呵
// 4 带搜索 str 为要搜索的字符串 匹配的元素排在前面
// Array.prototype.SortBy = function (type, str) {
//     switch (type) {
//         case 0:
//             this.sort();
//             break;
//         case 1:
//             this.sort(function (a, b) {
//                 return a - b;
//             });
//             break;
//         case 2:
//             this.sort(function (a, b) {
//                 return a.localeCompare(b)
//             });
//             break;
//         case 3:
//             this.sort(function () {
//                 return Math.random() > 0.5 ? -1 : 1;
//             });
//             break;
//         case 4:
//             this.sort(function (a, b) {
//                 return a.indexOf(str) === -1 ? 1 : -1;
//             });
//             break;
//         default:
//             this.sort();
//     }
// };

//一维数组的排序
// type 参数
// 0 字母顺序（默认）
// 1 大小 比较适合数字数组排序
// 2 拼音 适合中文数组
// 3 乱序 有些时候要故意打乱顺序，呵呵
// 4 带搜索 str 为要搜索的字符串 匹配的元素排在前面
// Array.prototype.SortByProperty = function (str) {
//     this[str]
// };

// 使用:
// var ary = [{id: 1, name: "b"}, {id: 2, name: "b"}];
// ary.sort(keysrt('name', true));
// ary.sort(keysrt('name', false));
// ary.sort(keysrt('id', false));

// function keysrt(key, desc) {
//     return function (a, b) {
//         if (a[key] = b[key]) return 0;
//         return desc ? ~~(a[key] < b[key]) : ~~(a[key] > b[key]);
//     }
// }
function keysrt(key, desc) {
    return function (a, b) {
        if (a[key] > b[key]) {
            return desc ? 1 : -1;
        } else if (a[key] < b[key]) {
            return desc ? -1 : 1;
        } else {
            return 0;
        }
        // if (a[key] = b[key]) return 0;
        // return desc ? ~~(a[key] < b[key]) : ~~(a[key] > b[key]);
    }
}


/** 数组排序

 *@param prop 属性字段

 *@returns 排序后的数组 {Array}

 */
// 但是在IE8中会出现排序不准的问题，可以用另一种方法来解决
function rank(prop) {
    return function (obj1, obj2) {
        var val1 = obj1[prop];
        var val2 = obj2[prop];
        if (!isNaN(Number(val1)) && !isNaN(Number(val2))) {
            val1 = Number(val1);
            val2 = Number(val2);
        }
        if (val1 < val2) { //降序排列
            return 1;
        } else if (val1 > val2) {
            return -1;
        } else {
            return 0;
        }
    };
}

/**
 * 获取URL链接参数值
 * @param name 参数key
 * @returns {null}
 */
function sbURL(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


/**
 * 后退事件
 */
function eventBack(callBack) {
    window.onload = function () {
        callBack()
    }
    // window.addEventListener("onpopstate", function (e) {
    //     console.warn(e)
    // });
    //
    // window.onbeforeunload()
    // window.onbeforeunload = function () {
    //     var n = window.event.screenX - window.screenLeft;
    //     var b = n > document.documentElement.scrollWidth - 20;
    //     if (b && window.event.clientY < 0 || window.event.altKey) {
    //         callBack();
    //         // alert("是关闭而非刷新");
    //         // window.event.returnValue = "是否关闭？";
    //         console.info("是关闭而非刷新")
    //     } else {
    //         console.info("是刷新而非关闭")
    //         // alert("是刷新而非关闭");
    //     }
    // }
}

