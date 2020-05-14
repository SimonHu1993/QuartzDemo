<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/PAGES/common/header.jsp"/>
<html>
<head>
    <title>定时调度管理</title>
</head>
<body width="100%" height="100%">
<table id="ListTable" title="定时调度管理" style="width: 100%;height: 100%"></table>
<div id="homepicAddWin"></div>
</body>
<script>
    $(function(){
        $('#ListTable').datagrid({
            url:'/api/jobs/queryAllData',
            striped:true,
            loadMsg:'数据加载中...请稍后......',
            pageList: [5,15,30,60,120],                      //可以设置每页记录条数的列表
            pageSize:30,
            singleSelect:true,                                     //是否单选
            pagination:true,                                        //分页控件
            rownumbers:true,                                       //行号
            nowrap:false,
            toolbar:[{   id: 'addSearch',
                text: '添加',
                iconCls: 'icon-add',
                handler:add},
                {   id: 'logSearch',
                    text: '日志',
                    iconCls: 'icon-search',
                    handler:log},

            ],
            columns: [[
                {field: 'id', title: '任务ID', width: '3%' , fitColumns:true, align:'center'},
                {field: 'jobName', title: '任务名称', width: '12%' , fitColumns:true, align:'center'},
                {field: 'beanName', title: 'Bean名称', width: '10%' , fitColumns:true, align:'center'},
                {field: 'methodName', title: '执行方法', width: '6%' , fitColumns:true, align:'center'},
                {field: 'params', title: '参数', width: '4%' , fitColumns:true, align:'center'},
                {field: 'cronExpression', title: 'cron表达式', width: '10%' , fitColumns:true, align:'center'},
                {field: 'isPause', title: '状态', width: '5%' , fitColumns:true, align:'center',
                    formatter: function (value,row,index) {
                        if(value == false){
                            return '<span style="color: green">运行中</span>';
                        }else {
                            return '<span style="color: red">已暂停</span>';
                        }
                    }},
                {field: 'remark', title: '描述', width: '14%' , fitColumns:true, align:'center'},
                {field: 'createTime', title: '创建日期', width: '14%' , fitColumns:true, align:'center',
                    formatter: function (value,row,index) {
                        var time = new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                        return time;
                    }},
                {field: ' ', title: '操作', width:'13%',fitColumns: true, fixed:true,align: 'center',
                    formatter: function(value,row,index){
                        var str= '<a style="color:blue" href="javascript:void(0)" onclick="edit(\''+row.id+'\')">'+"编辑"+'</a>   '
                          str+= '<a style="color:green" href="javascript:void(0)" onclick="execution(\''+row.id+'\')">'+"执行"+'</a>   '
                          str+= '<a style="color:orange" href="javascript:void(0)" onclick="updateIsPause(\''+row.id+'\')">'+(row.isPause?"恢复":"暂停")+'</a>   '
                          str+= '<a style="color:red" href="javascript:void(0)" onclick="del(\''+row.id+'\')">'+"删除"+'</a>  '
                        return str;
                    }, sortable: true},
            ]],

            fit:true,
        })
    });
    function log() {
        var $win;
        $win = $('#homepicAddWin').window({
            title:'调度日志',
            href:"/api/jobs/logForm",
            width: 1000,
            height: 400,
            shadow: true,
            modal: true,
            iconCls: 'icon-edit',
            closed: true,
            minimizable: false,
            maximizable: false,
            collapsible: false
        });
        $win.window('open');
    }
    function edit(id){
        var $win;
        $win = $('#homepicAddWin').window({
            title:'调度任务',
            href:"/api/jobs/jobForm?id="+id,
            width: 450,
            height: 300,
            shadow: true,
            modal: true,
            iconCls: 'icon-edit',
            closed: true,
            minimizable: false,
            maximizable: false,
            collapsible: false
        });
        $win.window('open');
    }
    function add(){
        var $win;
        $win = $('#homepicAddWin').window({
            title:'调度任务',
            href:"/api/jobs/jobForm",
            width: 450,
            height: 300,
            shadow: true,
            modal: true,
            iconCls: 'icon-edit',
            closed: true,
            minimizable: false,
            maximizable: false,
            collapsible: false
        });
        $win.window('open');
    }
    //执行
    function execution(id) {
        $.ajax({
            url: '/api/jobs/execution/'+id,
            data: {},
            type: 'post',
            cache: false,
            dataType: 'json',
            success: function (data) {
                 console.log(data)
                $('#ListTable').datagrid('reload');
            },error:function (data) {
                $.messager.alert('操作提示', '操作失败');
            }
        })
    }
    //更改定时任务
    function updateIsPause(id) {
        $.ajax({
            url: '/api/jobs/updateIsPause/'+id,
            data: {},
            type: 'post',
            cache: false,
            dataType: 'json',
            success: function (data) {
                 console.log(data)
                $('#ListTable').datagrid('reload');
            },error:function (data) {
                $.messager.alert('操作提示', '操作失败');
            }
        })
    }
    function del(id) {
        $.ajax({
            url: '/api/jobs/del/'+id,
            data: {},
            type: 'post',
            cache: false,
            dataType: 'json',
            success: function (data) {
                 console.log(data)
                $('#ListTable').datagrid('reload');
            },error:function (data) {
                $.messager.alert('操作提示', '操作失败');
            }
        })
    }
</script>
</html>