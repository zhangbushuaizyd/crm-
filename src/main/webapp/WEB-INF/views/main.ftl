<html>
<head>
    <#include "common.ftl" >
    <title>Crm | 后台主页</title>
    <script type="text/javascript" src="${ctx}/js/main.js"></script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
    <table style="padding: 5px" width="100%">
        <tr>
            <td width="50%">
                <img alt="logo" src="${ctx}/images/bglogo.png">
            </td>
            <td valign="bottom" align="right" width="50%">
                <font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${(user.userName)!"sxt"}</font>【${(user.trueName)!"sxt" }
                】<#--【${(user.roleName)!"系统管理员" }】-->
            </td>
        </tr>
    </table>
</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 100px"><font color="blue" size="10">欢迎使用Crm后台管理系统</font></div>
        </div>
    </div>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <#if permissions??>
            <#if permissions?seq_contains("10")>
        <div title="营销管理" data-options="selected:true,iconCls:'icon-yxgl'" style="padding: 10px">
                <#if permissions?seq_contains("1010")>
            <a href="javascript:openTab('营销机会管理','saleChance/index/1','icon-yxjhgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-yxjhgl'" style="width: 150px">营销机会管理</a>
                </#if>
                <#if permissions?seq_contains("1020")>
                    <a href="javascript:openTab('客户开发计划','saleChance/index/2','icon-khkfjh')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">客户开发计划</a>
                </#if>
        </div>
            </#if>
        </#if>
        <div title="客户管理" data-options="iconCls:'icon-khgl'" style="padding:10px;">
            <a href="javascript:openTab('客户信息管理','customer/index','icon-khxxgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khxxgl'" style="width: 150px;">客户信息管理</a>
            <a href="javascript:openTab('客户流失管理','customerLoss/index','icon-khlsgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khlsgl'" style="width: 150px;">客户流失管理</a>
        </div>
        <div title="服务管理" data-options="iconCls:'icon-fwgl'" style="padding:10px">
            <a href="javascript:openTab('服务创建','customerServe/index/1','icon-fwcj')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-fwcj'" style="width: 150px;">服务创建</a>
            <a href="javascript:openTab('服务分配','customerServe/index/2','icon-fwfp')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-fwfp'" style="width: 150px;">服务分配</a>
            <a href="javascript:openTab('服务处理','customerServe/index/3','icon-fwcl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-fwcl'" style="width: 150px;">服务处理</a>
            <a href="javascript:openTab('服务反馈','customerServe/index/4','icon-fwfk')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-fwfk'" style="width: 150px;">服务反馈</a>
            <a href="javascript:openTab('服务归档','customerServe/index/5','icon-fwgd')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-fwgd'" style="width: 150px;">服务归档</a>
        </div>
        <div title="统计报表" data-options="iconCls:'icon-tjbb'" style="padding:10px">
            <a href="javascript:openTab('客户贡献分析','report/0','icon-khgxfx')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khgxfx'" style="width: 150px;">客户贡献分析</a>
            <a href="javascript:openTab('客户构成分析','report/1','icon-khgcfx')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khgcfx'" style="width: 150px;">客户构成分析</a>
            <a href="javascript:openTab('客户服务分析','report/2','icon-khfwfx')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khfwfx'" style="width: 150px;">客户服务分析</a>
            <a href="javascript:openTab('客户流失分析','report/3','icon-khlsfx')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-khlsfx'" style="width: 150px;">客户流失分析</a>
        </div>
        <div title="基础数据管理" data-options="iconCls:'icon-jcsjgl'" style="padding:10px">
            <a href="javascript:openTab('数据字典管理','datadic/index','icon-sjzdgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-sjzdgl'" style="width: 150px;">数据字典管理</a>
            <a href="javascript:openTab('产品信息查询','product/index','icon-cpxxgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-cpxxgl'" style="width: 150px;">产品信息查询</a>
            <a href="javascript:openTab('用户信息管理','user/index','icon-user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">用户信息管理</a>
        </div>
        <div title="系统管理" data-options="iconCls:'icon-item'" style="padding:10px">
            <a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
            <a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'"
               style="width: 150px;">安全退出</a>

        <#--<#if  permissions?seq_contains("6010")>-->
            <a href="javascript:openTab('用户信息管理','user/index','icon-user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">用户管理</a>
        <#--</#if>-->
        <#--<#if permissions?seq_contains("6020")>-->
            <a href="javascript:openTab('角色管理','role/index','icon-user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">角色管理</a>
        <#--</#if>-->
        <#--<#if permissions?seq_contains("6030")>-->
            <a href="javascript:openTab('资源管理','module/index','icon-user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">资源管理</a>
        <#--</#if>-->
            <a href="javascript:openTab('柱状图','e01','icon-user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">柱状图</a>
            <a href="javascript:openTab('饼状图','e02','icon-user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">饼状图</a>

        </div>
    </div>
</div>
<div region="south" style="height:55px;background-color: #E0ECFF" align="center">
    版本所有  <a href="http://www.baidu.com" target="_blank">www.baidu.com</a>(2016-2026)<br/>
    管理系统代做 咨询电话：18655357100<br/>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:250px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="userName" name="userName" readonly="readonly" value="${(user.userName)! }"
                           style="width: 200px"/></td>
            </tr>
            <tr>
                <td>原密码：</td>
                <td><input type="password" id="oldPassword" name="oldPassword" class="easyui-validatebox"
                           required="true" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox"
                           required="true" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td><input type="password" id="newPassword2" name="confirmPassword" class="easyui-validatebox"
                           required="true" style="width: 200px"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>