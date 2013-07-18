<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<style type="text/css">
@import url(<%=contextPath%>/js/checkboxtree/jquery.checktree.css);
</style> 
<script language="javascript" src="<%=contextPath%>/js/checkboxtree/jquery-1.3.2.min.js"></script>
<script language="javascript" src="<%=contextPath%>/js/checkboxtree/jquery.checktree_yctin.min.js"></script>
<script language="javascript" src="<%=contextPath%>/js/checkboxtree/jquery.updateWithJSON.min.js"></script>
        <script type="text/javascript">
            var $checktree;
            $(function(){
                $checktree = $("ul.tree").checkTree();
            });
                     var  mang={"ids[]":["211","210","200"]};

			function load(id,mang) {
                        mang={"ids[]":["211","210","200"]};
				$.getJSON("<%=contextPath%>/js/checkboxtree/data/" + id + ".txt",{},function(json){
					$checktree.clear();
					$.updateWithJSON({"ids[]":["369","210","200"]});
					$checktree.update();
				});
			}
			function clearAll(){
				$checktree.clear();
				$checktree.update();
			}
                        load(2,mang);
        </script>
        <ul class="tree">
            <li>
                <input type="checkbox">
                <label>
                    Da Nang
                </label>
                        <ul>
           <logic:iterate id="bean" name="BDepartments" type="com.form.admin.departments.FDepartment">
              <%
              if(bean.getParentID()==0){%>
                                <li>
                                    <input name='ids[]' value="<%=bean.getId()%>" type="checkbox">
                                        <label><%=bean.getName()%></label>
                                </li>
              <%}%>

              </logic:iterate>
                        </ul>
              </li>
        </ul>
        
  
        <br style="clear:both"/>
