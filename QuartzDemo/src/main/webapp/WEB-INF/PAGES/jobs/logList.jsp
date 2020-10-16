<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>

<body>
<table id="Listlog" title="" style="width: 70%;height: 50%"></table>
<script>
    $(function () {
        $('#Listlog').datagrid({
            url: '/api/jobs/queryAllLog',
            striped: true,
            loadMsg: '数据加载中...请稍后......',
            pageList: [5, 15, 30, 60, 120],                      //可以设置每页记录条数的列表
            pageSize: 15,
            singleSelect: true,                                     //是否单选
            pagination: true,                                        //分页控件
            rownumbers: true,                                       //行号
            nowrap: false,
            toolbar: [],
            columns: [[
                {field: 'jobName', title: '任务名称', width: '12%', fitColumns: true, align: 'center'},
                {field: 'beanName', title: 'Bean名称', width: '10%', fitColumns: true, align: 'center'},
                {field: 'methodName', title: '执行方法', width: '16%', fitColumns: true, align: 'center'},
                {field: 'params', title: '参数', width: '8%', fitColumns: true, align: 'center'},
                {field: 'cronExpression', title: 'cron表达式', width: '10%', fitColumns: true, align: 'center'},
                {
                    field: 'exceptionDetail', title: '异常详情', width: '7%', fitColumns: true, align: 'center',
                    formatter: function (value, row, index) {
                        if (value == '1') {
                            var str = '<a style="color:blue" href="javascript:void(0)" onclick="check(\'' + row.id + '\')">' + "查看" + '</a>  '
                            return str;
                        } else {
                            return '';
                        }
                    }
                },
                {field: 'time', title: '耗时(毫秒)', width: '10%', fitColumns: true, align: 'center'},
                {
                    field: 'isSuccess', title: '状态', width: '7%', fitColumns: true, align: 'center',
                    formatter: function (value, row, index) {
                        if (value == false) {
                            return '<span style="color: red">失败</span>';
                        } else {
                            return '<span style="color: green">成功</span>';
                        }
                    }
                },
                {
                    field: 'createTime', title: '创建日期', width: '14%', fitColumns: true, align: 'center',
                    formatter: function (value, row, index) {
                        var time = new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                        return time;
                    }
                },
            ]],

            fit: true,
        })
    });

    function check(id) {
        window.open("/api/jobs/exceptionInfo?id="+id,"_blank")
    }
</script>

</body>
</html>
