$(function () {
    var lossId = $('#lossId').val();
    $('#dg').edatagrid({
        url: ctx + "/customerReprieve/queryCustomerReprievesByParams?lossId="+lossId,
        saveUrl: ctx + "/customerReprieve/saveOrUpdateCustomerReprieve?lossId="+lossId,
        updateUrl: ctx + "/customerReprieve/saveOrUpdateCustomerReprieve?lossId="+lossId,
        destroyUrl: ctx+ "/customerReprieve/delReprieve"
    });
    // 初始判断,开发状态是DevResult为2和3的时候,隐藏工具条,设置为不可编辑
    // 解决是开发状态但是任然可以打开窗口
    var state= $("#state").val();
    if (state == 1){
        //隐藏工具条
        $('#toolbar').hide();
        //使得表格不可编辑
        $('#dg').edatagrid('disableEditing')
    }
});

function addRow() {
    $('#dg').edatagrid("addRow");
}

function saveOrUpdateCusDevPlan() {
    $('#dg').edatagrid("saveRow");
}

function delCusDevPlan() {
    $('#dg').edatagrid("destroyRow");
}

// 确认流失按钮
function confirmLoss() {
    var lossId = $('#lossId').val();
    $.messager.confirm('来自Crm',"是否确认流失",function (r) {
        if (r){
            //发送ajax请求
            $.ajax({
                url:ctx+"/customerLoss/updateCustomerLoss",
                data:{
                    id:lossId,
                    state:1
                },
                success:function (data) {
                    if (data.code == 200){
                        $.messager.alert('来自Crm',data.msg,'info',function () {
                            //隐藏工具条
                            $('#toolbar').hide();
                            //使表格不可编辑
                            // 使表格不可编辑
                            $('#dg').edatagrid("disableEditing")
                        });
                    }else {
                        $.messager.alert('来自Crm', data.msg, 'error');
                    }
                }
            });
        }
    })
}