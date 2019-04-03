<#include "common.ftl" >
  <script type="text/javascript">
    alert('${errorMsg}');
    <#--window.location.href="${ctx}/index";-->

    if('${uri}'=="/main"){
        window.location.href="${ctx}/index";
    }else{
        window.parent.location.href="${ctx}/index";
    }
    
  </script>
