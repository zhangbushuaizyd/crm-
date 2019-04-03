function queryCustomersByParams() {
    $('#dg').datagrid('load', {
        name: $('#cusName').val(),
        khno: $('#khno').val(),
        fr: $('#fr').val()
    });
}
// 添加按钮
function openAddCustomerDialog() {
    openAddOrUpdateDlg('dlg','添加客户信息')
}

function  closeCustomerDialog() {
    closeDlgData("dlg");
}

function saveOrUpdateCustomer() {
    saveOrUpdateData("fm",ctx+"/customer/saveOrUpdateCustomer","dlg",queryCustomersByParams);
}
// 更新操作
function openModifyCustomerDialog() {
    openModifyDialog("dg","fm","dlg","更新客户信息");
}

//删除
function deleteCustomer() {
    deleteData('dg',ctx+'/customer/deleteCustomer',queryCustomersByParams);
}

$(function () {
    $('#dlg').dialog({
        "onClose":function () {
            // 触发表单清空
            $('#fm').form('clear');
        }
    })
})

function openCustomerOtherInfo(title,typer) {
    var rows = $('#dg').datagrid("getSelections");
    // console.log(rows);
    if (rows.length ==0){
        $.messager.alert('来自Crm','请选择一条记录')
        return;
    }
    if (rows.length >1){
        $.messager.alert('来自Crm','只能选择一条记录')
        return;
    }
    var cusId=rows[0].id;
    if (typer==3){
        window.parent.openTab(title+"_"+cusId,ctx+"/customerOrder/index?cid="+cusId);
    }
}
