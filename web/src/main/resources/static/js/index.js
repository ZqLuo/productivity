function addTab(tabid ,title, url){
    var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
    if ($('#'+tabid).tabs('exists', title)){
        $('#'+tabid).tabs('select', title);
        var tab = $('#'+tabid).tabs('getSelected');
        $('#'+tabid).tabs('update', {
            tab: tab,
            options: {
                title: title,
                content:content,
                closable:true
            }
        });
    } else {
        $('#'+tabid).tabs('add',{
            title:title,
            content:content,
            closable:true
        });
    }
}
var size = {
    width : document.documentElement.clientWidth,
    height : document.documentElement.clientHeight
}

function fixWidthWithOptWidth(percent) {
    return  Math.round((Number(size.width-optwidth)) * percent);
}

/**
 * 字符串转日期
 * @param str
 */
function strToDate(str) {
    if(str && str != ''){
        return new Date(str.replace(/-/g, "/"));
    }
    return "";
}

/**
 * 日期转换，类似这种格式2019-01-01T00:00:00.000+0000
 * @param date
 * @returns {string}
 */
function tDateToStr(date,formatStr) {
    if(date == null || date == '') return '';
    var arr=date.split("T");
    var d=arr[0];
    var darr = d.split('-');
    var t=arr[1];
    var tarr = t.split('.000');
    var marr = tarr[0].split(':');
    var curDate = new Date();
    curDate.setFullYear(parseInt(darr[0]), parseInt(darr[1])-1, parseInt(darr[2]));
    curDate.setHours(parseInt(marr[0]), parseInt(marr[1]), parseInt(marr[2]));
    return formatDate(curDate,formatStr);
}
//---------------------------------------------------
// 日期格式化
// 格式 YYYY/yyyy/YY/yy 表示年份
// MM/M 月份
// W/w 星期
// dd/DD/d/D 日期
// hh/HH/h/H 时间
// mm/m 分钟
// ss/SS/s/S 秒
//---------------------------------------------------
function formatDate(fd,formatStr)
{
    var str = formatStr;
    var Week = ['日','一','二','三','四','五','六'];

    str=str.replace(/yyyy|YYYY/,fd.getFullYear());
    str=str.replace(/yy|YY/,(fd.getYear() % 100)>9?(fd.getYear() % 100).toString():'0' + (fd.getYear() % 100));
    var month = fd.getMonth()+1;
    str=str.replace(/MM/,month>9?month.toString():'0' + month);
    str=str.replace(/M/g,month);

    str=str.replace(/w|W/g,Week[fd.getDay()]);

    str=str.replace(/dd|DD/,fd.getDate()>9?fd.getDate().toString():'0' + fd.getDate());
    str=str.replace(/d|D/g,fd.getDate());

    str=str.replace(/hh|HH/,fd.getHours()>9?fd.getHours().toString():'0' + fd.getHours());
    str=str.replace(/h|H/g,fd.getHours());
    str=str.replace(/mm/,fd.getMinutes()>9?fd.getMinutes().toString():'0' + fd.getMinutes());
    str=str.replace(/m/g,fd.getMinutes());

    str=str.replace(/ss|SS/,fd.getSeconds()>9?fd.getSeconds().toString():'0' + fd.getSeconds());
    str=str.replace(/s|S/g,fd.getSeconds());

    return str;
}

//自动转换数字金额为大小写中文字符,返回大小写中文字符串，最大处理到999兆
function changeMoneyToChinese( money )
{
    var cnNums = new Array("零","壹","贰","叁","肆","伍","陆","柒","捌","玖"); //汉字的数字
    var cnIntRadice = new Array("","拾","佰","仟"); //基本单位
    var cnIntUnits = new Array("","万","亿","兆"); //对应整数部分扩展单位
    var cnDecUnits = new Array("角","分","毫","厘"); //对应小数部分单位
    var cnInteger = "整"; //整数金额时后面跟的字符
    var cnIntLast = "元"; //整型完以后的单位
    var maxNum = 999999999999999.9999; //最大处理的数字

    var IntegerNum; //金额整数部分
    var DecimalNum; //金额小数部分
    var ChineseStr=""; //输出的中文金额字符串
    var parts; //分离金额后用的数组，预定义

    if( money == "" ){
        return "";
    }

    money = parseFloat(money);
    //alert(money);
    if( money >= maxNum ){
        $.alert('超出最大处理数字');
        return "";
    }
    if( money == 0 ){
        ChineseStr = cnNums[0]+cnIntLast+cnInteger;
        //document.getElementById("show").value=ChineseStr;
        return ChineseStr;
    }
    money = money.toString(); //转换为字符串
    if( money.indexOf(".") == -1 ){
        IntegerNum = money;
        DecimalNum = '';
    }else{
        parts = money.split(".");
        IntegerNum = parts[0];
        DecimalNum = parts[1].substr(0,4);
    }
    if( parseInt(IntegerNum,10) > 0 ){//获取整型部分转换
        zeroCount = 0;
        IntLen = IntegerNum.length;
        for( i=0;i<IntLen;i++ ){
            n = IntegerNum.substr(i,1);
            p = IntLen - i - 1;
            q = p / 4;
            m = p % 4;
            if( n == "0" ){
                zeroCount++;
            }else{
                if( zeroCount > 0 ){
                    ChineseStr += cnNums[0];
                }
                zeroCount = 0; //归零
                ChineseStr += cnNums[parseInt(n)]+cnIntRadice[m];
            }
            if( m==0 && zeroCount<4 ){
                ChineseStr += cnIntUnits[q];
            }
        }
        ChineseStr += cnIntLast;
        //整型部分处理完毕
    }
    if( DecimalNum!= '' ){//小数部分
        decLen = DecimalNum.length;
        for( i=0; i<decLen; i++ ){
            n = DecimalNum.substr(i,1);
            if( n != '0' ){
                ChineseStr += cnNums[Number(n)]+cnDecUnits[i];
            }
        }
    }
    if( ChineseStr == '' ){
        ChineseStr += cnNums[0]+cnIntLast+cnInteger;
    }
    else if( DecimalNum == '' ){
        ChineseStr += cnInteger;
    }
    return ChineseStr;
}

/**
 * 校验Combobox输入的文字是否包含在下拉框中，true代表包含，false代表不包含
 * @param id
 * @returns {boolean}
 */
function checkComboBoxValue(id){
    var value=$("#"+id).combobox('getValue');
    var valueField = $("#"+id).combobox("options").valueField;
    var allData = $("#"+id).combobox("getData");
    var result = false;
    for (var i = 0; i < allData.length; i++) {
        if (value == allData[i][valueField]) {
            result = true;
            break;
        }
    }
    return result;
}



/**
 * 给时间框控件扩展一个清除的按钮
 */
$.fn.datebox.defaults.cleanText = '清空';

(function ($) {
    var buttons = $.extend([], $.fn.datebox.defaults.buttons);
    buttons.splice(1, 0, {
        text: function (target) {
            return $(target).datebox("options").cleanText
        },
        handler: function (target) {
            $(target).datebox("setValue", "");
            $(target).datebox("hidePanel");
        }
    });
    $.extend($.fn.datebox.defaults, {
        buttons: buttons
    });

})(jQuery)