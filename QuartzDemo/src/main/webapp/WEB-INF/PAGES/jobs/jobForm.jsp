<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href="/css/m_style.css"/>
    <jsp:include page="/WEB-INF/PAGES/common/header.jsp"/>
</head>
<body>
<div align="left" style="margin-left:50px;margin-top: 10px;">
    <form id="form">
        <input type="hidden" id="id" name="id" value="${result.id}"/>
        <input type="hidden" id="status" name="status" value="1"/>
        <table>
            <tr>
                <div>
                    <td><label for="jobName">任务名称 ：</label></td>
                    <td><input type="text" id="jobName" name="jobName" class="easyui-textbox"
                               data-options="required:true"
                               value="${result.jobName}" style="width: 200px"/></td>
                </div>
            </tr>
            <tr>
                <div>
                    <td><label for="beanName">Bean名称：</label></td>
                    <td><input type="text" id="beanName" name="beanName" class="easyui-textbox"
                               data-options="required:true" value="${result.beanName}"
                               style="width: 200px"/></td>
                </div>
            </tr>
            <tr>
                <div>
                    <td><label for="isPause">状态：</label></td>
                    <td>
                        <select id="isPause" name="isPause" style="width: 80px" class="easyui-combobox"
                                data-options="required:true" value="${result.isPause}">
                            <option value="true">暂停</option>
                            <option value="false">启用</option>
                        </select>
                    </td>
                </div>
            </tr>
            <tr>
                <div>
                    <td><label for="methodName">执行方法：</label></td>
                    <td><input type="text" id="methodName" name="methodName" class="easyui-textbox"
                               data-options="required:true" value="${result.methodName}"
                               style="width: 200px"/></td>
                </div>
            </tr>
            <tr>
                <div>
                    <td><label for="params">参数：</label></td>
                    <td><input type="text" id="params" name="params" class="easyui-textbox"
                               value="${result.params}"
                               style="width: 200px"/></td>
                </div>
            </tr>
            <tr>
                <div>
                    <td><label for="cronExpression">cron表达式：</label></td>
                    <td><input type="text" id="cronExpression" name="cronExpression" class="easyui-textbox"
                               data-options="required:true" value="${result.cronExpression}"
                               style="width: 200px"/></td>
                </div>
            </tr>
            <tr>
                <div>
                    <td><label for="remark">描述：</label></td>
                    <td><input type="text" id="remark" name="remark" class="easyui-textbox"
                               value="${result.remark}"
                               style="width: 200px"/></td>
                </div>
            </tr>
        </table>
        <br><br>
        <div style="padding-left: 150px;">
            <a id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="submit();">保存</a>
        </div>
    </form>

</div>
<script>
    $(function () {
        var interval = window.setInterval(function () {
            //等待页面加载完毕
            if($("#isPause").combobox().length > 0){
                $("#isPause").combobox('setValue','${result.isPause}');
                window.clearInterval(interval);
            }
        },100)
    })
    /**
     * 提交
     */
    function submit() {
        $('#form').form('submit', {
            url: '/api/jobs/update',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                $('#ListTable').datagrid('reload');

                data = $.parseJSON(data);
                if (data.code != "000") {
                    $.messager.alert('操作提示', data.msg);
                } else {
                    $win = $('#homepicAddWin');
                    $win.window('close');
                    $.messager.alert('操作提示', data.msg);
                    $('#form').form('clear');
                }
            }
        });
    }
</script>
</body>
</html>

