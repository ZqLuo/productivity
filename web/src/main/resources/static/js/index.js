function addTab(tabid ,title, url){
    if ($('#'+tabid).tabs('exists', title)){
        $('#'+tabid).tabs('select', title);
    } else {
        var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
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