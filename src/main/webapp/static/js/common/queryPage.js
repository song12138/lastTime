;(function($){
    $.queryPage = {

        /**
         * 分页查询
         * @param tableId 表格所在div的id
         * @param tempId jsrender所用js模板id
         * @param url   向后台请求数据id
         * @param page 页码
         * @param rows 每页显示条数
         * @param param 后台传递参数
         */
        query:function (tableId,temId,url,pageNo,pageSize,param) {
            url=this.getRootPath()+"/"+url;
            this.ajax_(tableId, temId, url, pageNo, pageSize, param);

        },
        getRootPath:function(){
            //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
            var curWwwPath=window.document.location.href;
            //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
            var pathName=window.document.location.pathname;
            var pos=curWwwPath.indexOf(pathName);
            //获取主机地址，如： http://localhost:8083
            var localhostPaht=curWwwPath.substring(0,pos);
            //获取带"/"的项目名，如：/uimcardprj
            var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
            return(localhostPaht+projectName);
        },
        ajax_:function (tableId, temId, url, pageNo, pageSize, param) {
            param['pageNo'] = pageNo;
            param['pageSize'] = pageSize;
            $.ajax({
                type:'post',
                url:url,
                dataType:'json',
                data:param,
                success:function (result) {
                    console.log(result);
                    $('#' + tableId).html($('#' + temId).render(result));
                }
            });

        }




    };
})(jQuery);