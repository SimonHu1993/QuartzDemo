




/**
 * Created by admin on 2015/11/4.
 */
/**
 * ʹ�÷���:
 * ����:MaskUtil.mask();
 * �ر�:MaskUtil.unmask();
 *
 * MaskUtil.mask('������ʾ����...');
 */
var MaskUtil = (function(){

    var $mask,$maskMsg;

    var defMsg = '���ڴ������Դ�������';

    function init(){
        if(!$mask){
            $mask = $("<div class=\"datagrid-mask mymask\"></div>").appendTo("body");
        }
        if(!$maskMsg){
            $maskMsg = $("<div class=\"datagrid-mask-msg mymask\">"+defMsg+"</div>")
                .appendTo("body").css({'font-size':'12px'});
        }

        $mask.css({width:"100%",height:$(document).height()});

        var scrollTop = $(document.body).scrollTop();

        $maskMsg.css({
            left:( $(document.body).outerWidth(true) - 190 ) / 2
            ,top:( ($(window).height() - 45) / 2 ) + scrollTop
        });

    }

    return {
        mask:function(msg){
            init();
            $mask.show();
            $maskMsg.html(msg||defMsg).show();
        }
        ,unmask:function(){
            $mask.hide();
            $maskMsg.hide();
        }
    }

}());