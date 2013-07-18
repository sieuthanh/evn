var empDep=',';
function processEmps(obj){  
    var i = empDep.indexOf(','+obj.value, 0);  
    if(obj.checked){
        if(i<0) empDep+=obj.value + ',';
    }else{               
        if(i>=0) empDep=empDep.substr(0,i) + empDep.substr(i+obj.value.length+1,empDep.length);        
    }   
    //document.createMessage.checkAllEmp.checked=false;  
}

//var empDep="";
//function addMemeber(obj){
//       if(obj.checked){
//             if(empDep!='')  empDep += ','
//                empDep += obj.value;
//      }else{
//            empDep=empDep.replace(obj.value,"");
//      }
//      empDep=empDep.replace(",,",",");
//}


function checkX(obj){
    var check=obj.checked;
    var objs = obj.form.disposeUser;   
    var thisvalue = obj.value.split("#")[1];
    if(typeof objs !="undefined"){
      if(typeof objs.length !="undefined"){
        for (i=0;i<objs.length;i++){
            var valuex=objs[i].value.split("#")[1];
            if(valuex==thisvalue){
                objs[i].checked = false;//loai bo ra khoi mang
                var aa=obj.form.members.value;
                obj.form.members.value=aa.replace(objs[i].value,"");
            }
        }
         obj.checked =check;
         obj.form.members.value=addRemoveMembers(obj);
      }else{
          if(objs.checked){
             obj.form.members.value=objs.value;         
          }
      }
    }
}

function checkY(obj){
    var check=obj.checked;
    var objs = obj.form.disposeUser;
    var thisvalue = obj.value.split("#")[0];
    if(typeof objs !="undefined"){
      if(typeof objs.length !="undefined"){
        for (i=0;i<objs.length;i++){
            var valuex=objs[i].value.split("#")[0];
            if(valuex==thisvalue){
                objs[i].checked = false;//loai bo ra khoi mang
                var aa=obj.form.members.value;
                obj.form.members.value=aa.replace(objs[i].value,"");
            }
        }
         obj.checked =check;
         obj.form.members.value=addRemoveMembers(obj);
      }else{
          if(objs.checked){
             obj.form.members.value=objs.value;         
          }
      }
    }
    
    
}

function addRemoveMembers(objs){ 
    var members=objs.form.members.value;
    if(objs.checked){
            if(members!='')  members += ','
            members += objs.value;
    }else{
            members=members.replace(objs.value,"");
    }
    members=members.replace(",,",",")        
    return members;
}
function viewReport(id){ 
        message('tdodyList','Xin &#273;&#7907;i, &#273;ang t&#7843;i d&#7919; li&#7879;u...'); 
        postAjax('reportSystem','tdodyList',anchor +':_VIEW:id:'+id+'');
}
function viewReportSum(id){ 
        post('reportSystemSum',anchor +':_VIEW:id:'+id+'');
}


var checkClose=null;

function orderAjax(form,id,params){     
    postAjax(form,id,params);  
    message('MainMessage','Xin ch&#7901; trong gi&#226;y l&#225;t...')
}
function navigatorGe(formName,objResult,params){     
         postAjax(formName,objResult,params);
         message('MainProplem','Xin ch&#7901; trong gi&#226;y l&#225;t...');
    } 

function onclickTr(obj,styleClass){
		var totalstd=obj.parentNode.cells;
		for(i=0;i<totalstd.length;i++){
			totalstd[i].className=totalstd[i].className==''?styleClass:'';
			if(i==1) {
			totalstd[1].childNodes[0].checked=totalstd[i].className==''?false:true; 
			}
		}
}

function removeItem(obj,form,index) {
        for (i=obj.length-1;i>=0;i--){                               
            if (obj.options[i].selected){  
               if (index==1){
                  for (k=form.incharge.length-1;k>0;k--){
                        if (form.incharge.options[k].value==obj.options[i].value){
                                form.incharge.remove(k);
                        }
                  }
               }
                obj.remove(i);
            }
        }
} 


function removeItemPrio(obj,objRemove,form,index) {
       
        for (i=obj.length-1;i>=0;i--){                               
            if (obj.options[i].selected){         
                for (j=objRemove.length-1;j>=0;j--){           
                    if (objRemove.options[j].value==obj.options[i].value){  
                       
                            objRemove.remove(j);
                    }
                }
            }
        }
} 


function addUserCheckExits(id,obj) {
    
    check = false ;
    for (i=obj.length-1;i>=0;i--){                               
         if (obj.options[i].value == id){
               check=true 
               break;
        }    
    }
   return check;
} 


function checkAll(checkname,exby) { 
   if (checkname!=null){
         if(checkname.length>1){
           for (i = 0; i < checkname.length; i++){          
             checkname[i].checked = exby.checked;    
           }    
         }else if(!checkname.length){       
           checkname.checked = exby.checked;   
         }
    }
}

function checkedAll(obj) {      
    for (i=0;i<obj.length;i++){   
         obj.options[i].selected = true; 
    } 
    return obj.length;
}

function AddUser(destination,obj) {
if(destination!=null){
     var check = false ;
     var id= obj.value;
     var valueText = obj.options[obj.selectedIndex].text;
     for (i = 0; i < destination.length; i++) {                              
          if (destination.options[i].value == id){
               check=true;
               break;
          }                              
      }  
      if (!check){ 
          destination.options[destination.length] = new Option(valueText, id);
      }
}else{
return false;
}
}
function AddAllUser(destination,obj) {                    
     if (obj!=null && destination!=null){
         for (k=0;k<obj.length;k++){       
             check = false;
             for (i = 0; i < destination.length; i++) {                              
                     if (destination.options[i].value == obj.options[k].value){                               
                           check=true;
                           break;
                      }                                     
             }  
            if (!check){             
                destination.options[destination.length] = new Option(obj.options[k].text, obj.options[k].value);
            }
        }
      }
}
//function AddUserPri(id,destination,valueText) {      
//    check = false ;
//     for (i = 0; i < destination.length; i++) {                              
//          if (destination.options[i].value == id){
//               check=true 
//               break;
//          }                              
//      }  
//      if (!check){ 
//          destination.options[destination.length] = new Option(valueText, id);
//      }
//}

function message(id,msg){
    var msgbox='<table style="border-right: #586B7A 1px solid;border-top: #DEE6E9 1px solid;border-left: #DEE6E9 1px solid;border-bottom: #586B7A 1px solid;" align="center">' +
               '<tr height=30px><td><IMG src="images/loading.gif"></td>' + 
               '<td align="center"><span style="font-family:Tahoma,Arial;font-size=10px;FONT-WEIGHT: bold;">' + msg + '</span></td>' +
               '</tr></table>';
    try{ 
        getObj(id).innerHTML = msgbox;   
    } catch (ex) {}
}

function messageImg(id){
    var msgbox='<table style="border: #586B7A 1px solid;" align="center">' +
               '<tr height=30px><td><IMG src="images/loading.gif"></td>' + 
               '<td align="center"><span style="font-family:Tahoma,Arial;font-size=10px;FONT-WEIGHT: bold;">&#272;ang t&#7843;i d&#7919; li&#7879;u ...</span></td>' +
               '</tr></table>';
    try{ 
        getObj(id).innerHTML = msgbox;   
    } catch (ex) {}
}

function trim(s) {
    return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
}

function getObj(id){  
    var d=document;
    var obj=d.getElementById(id);
    if(obj==null){
            try{ obj=d.all(id);} catch(ex){};
            if(obj==null){
               try{ obj=d.layers[id];}catch(ex){};
            }
    }
    if(obj==null && id.innerHTML) {
        obj=id;
    }    
    return obj;
}
function genCode(inputItem, outName){
    var ps= inputItem.split(" ");    
    var result = '';
    for(i=0;i<ps.length;i++){
        var item = trim(ps[i]);
        if(item!=''){
            result += item.charAt(0); 
        }
    }    
    var obj =getObj(outName);
    if(obj!=null && trim(obj.value)==''){
      obj.value = result.toUpperCase();
    }
}
function post(formName,params){
		var postForm = document.forms[formName];
		var ps=params.split(":");
		if(1==(ps.length%2)) return;
        var obj = new Array();
        var param;
        var i;
        for(i=0;i<ps.length/2;i++){
            param = document.createElement("input");
            param.type = "hidden";
            param.name = ps[2*i];
            param.value= encodeURI(ps[2*i+1]);
            postForm.appendChild(param);
            obj[i]=param;
        }
        postForm.submit();
        for(i=0;i<obj.length;i++){
          try{
              postForm.removeChild(obj[i]);
          }  catch (ex)  {}
        }
}
function hideshow(id,value)
{
  if(value){
    getObj(id).style.display = (value)?'none':'block';
  }else{
    var  display = getObj(id).style.display;
    getObj(id).style.display = (display=='' || display=='block')?'none':'block';
  }
}


 function checkedInnerHtml(){         
        var obj = getObj('addthis_dropdown15');  
       
        if (obj!=null) {       
            obj.innerHTML = null;       
        }
        if (obj!=null && obj.style.display =='block'){           
           addthis_closewin15();
        }
    }

function remove(formName,objName){
  var postForm = document.forms[formName];
  if(postForm.elements!=null)
     for(i=0;i<postForm.elements.length;i++){
            if(postForm.elements[i].name==objName){
                postForm.removeChild(postForm.elements[i]);
            }
     }
}


function minimize(id, minimize)
{ 

   var section  = getObj("h3" + id);
   var section1 = getObj("subContent"+id);
  
   if (section.className == "titleLeftEmail contents-toggler-down"){
         section.className="contents-toggler titleLeftEmail"
         section1.className="myContactClose"
   }else{
         section.className="titleLeftEmail contents-toggler-down"
         section1.className="myContactOpen"
   } 
   
}

function clear(){
if(document.getElementById('divAlert')!=null){
    document.getElementById('divAlert').innerHTML='';
}else{return false;}
}

var detailReport=null;
var idname=null;

function showDetail(formName,posittion,params,index){
       if ((idname==posittion + index) &&  (detailReport!=null && detailReport.innerHTML!='')){
             detailReport.innerHTML='';     
       }else{        
             if (detailReport!=null && detailReport.innerHTML!='') detailReport.innerHTML=''; 
             postAjax(formName,posittion+index,anchor + params);
             messageImg(posittion+index)   
        }
        idname = posittion + index;
        detailReport=getObj(posittion +index);
   }
   
   function showDetailCallPack(formName,posittion,params,index,functionCallPack){
       if ((idname==posittion + index) &&  (detailReport!=null && detailReport.innerHTML!='')){
             detailReport.innerHTML='';     
       }else{        
             if (detailReport!=null && detailReport.innerHTML!='') detailReport.innerHTML=''; 
             postAjax(formName,posittion+index,anchor + params,functionCallPack);
             messageImg(posittion+index)   
        }
        idname = posittion + index;
        detailReport=getObj(posittion +index);
   }
   
   function doCheckAllInDiv(parentId){
var collection = document.getElementById(parentId).getElementsByTagName('INPUT');
var flag = collection[0].checked;
for (var x=0; x<collection.length; x++) {
if (collection[x].type.toUpperCase()=='CHECKBOX')
collection[x].checked = flag;
collection[0].checked = flag;
}
}

function checkHideComboBox(){
    if(document.getElementById('SearchProduct').style.display=='block'){
        document.getElementById('SearchProduct').style.display = 'none';
    }
    if(document.getElementById('SearchProduct2').style.display=='block'){
        document.getElementById('SearchProduct2').style.display = 'none';
    }
}