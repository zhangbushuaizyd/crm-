//格式化数据显示
function formateGrade(value, row, index){
    if (0 == value){
        return "根级菜单";
    }
    if (1 == value){
        return "一级菜单";
    }
    if (2 == value){
        return "二级菜单";
    }
}

// 查询方法

function queryModulesByParams() {
    $('#dg').datagrid('load',{
        moduleName:$('#customerName').val(),
        grade:$('#grade').combobox('getValue'),
        parentId:$('#pid').val(),
        optValue:$('#optValue').val()
    })
}


$(function () {
        $('#dlg').dialog({
            "onClose": function () {
                // 触发表单清空
                $('#fm').form('clear');
            }
        })

    // 清空表单
    //判断是否显示第二个下拉
    $('#parentMenu').hide();

$('#grade02').combobox({
    onSelect: function (val) {
        var grade = val.value;
        if(grade==0){
            $('#parentMenu').hide();
        }else{
            $('#parentMenu').show();
            $('#parentId02').combobox({
                url:ctx + '/module/queryModulesByGrade?grade='+(grade-1),// 找上级
                valueField:'id',
                textField:'moduleName'
            });
        }
    }
})
    }
)




function openAddModuleDailog() {
    openAddOrUpdateDlg('dlg','添加模块');
}

function saveOrUpdateModule() {
    saveOrUpdateData('fm',ctx + '/module/saveOrUpdateModule','dlg',queryModulesByParams);
}

// 删除
function deleteModule() {
    deleteData('dg',ctx + '/module/deleteModule', queryModulesByParams);
}