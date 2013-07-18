// JavaScript Document
btn_01 = new Image();
btn_01.src = "../../images/newImages/tool_2.gif";
btn_02 = new Image();
btn_02.src = "../../images/newImages/tool_3.gif";
btn_03 = new Image();
btn_03.src = "../../images/newImages/tool_4.gif";
btn_04 = new Image();
btn_04.src = "../../images/newImages/tool_5.gif";
btn_05 = new Image();
btn_05.src = "../../images/newImages/tool_6.gif";
btn_06 = new Image();
btn_06.src = "../../images/newImages/tool_7.gif";
btn_07 = new Image();
btn_07.src = "../../images/newImages/tool_8.gif";
btn_08 = new Image();
btn_08.src = "../../images/newImages/tool_9.gif";
btn_09 = new Image();
btn_09.src = "../../images/newImages/tool_10.gif";
btn_10 = new Image();
btn_10.src = "../../images/newImages/tool_12.gif";

btn_11 = new Image();
btn_11.src = "../../images/newImages/tool_2_select.gif";
btn_12 = new Image();
btn_12.src = "../../images/newImages/tool_3_select.gif";
btn_13 = new Image();
btn_13.src = "../../images/newImages/tool_4_select.gif";
btn_14 = new Image();
btn_14.src = "../../images/newImages/tool_5_select.gif";
btn_15 = new Image();
btn_15.src = "../../images/newImages/tool_6_select.gif";
btn_16 = new Image();
btn_16.src = "../../images/newImages/tool_7_select.gif";
btn_17 = new Image();
btn_17.src = "../../images/newImages/tool_8_select.gif";
btn_18 = new Image();
btn_18.src = "../../images/newImages/tool_9_select.gif";
btn_19 = new Image();
btn_19.src = "../../images/newImages/tool_10_select.gif";
btn_20 = new Image();
btn_20.src = "../../images/newImages/tool_12_select.gif";


function swapImage(imgDocID,imgObjName) {
alert(imgObjName);
	document.images[imgDocID].src = eval(imgObjName + ".src")
}

function findPosXY(obj)
{
	var XY={x:0, y:0};
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			XY.x += obj.offsetLeft;
			XY.y += obj.offsetTop;
			obj = obj.offsetParent;
		}
	}
	else if (obj.x){
		XY.x += obj.x;
		XY.y += obj.y;
	}
	return XY;
}

function setStyle(el, prop) {
	if (document.all) {
		var props = prop.split(';');
		for (var i = 0; i < props.length; i++) {
			var attrib = props[i].split(':');
			if(attrib[0] != '' && attrib[0] != 'undefined') eval("el.style."+attrib[0]+"='"+attrib[1]+"';");
		}
	}else{
			var cssText = el.style.cssText;
			var cssTexts = cssText.split(';');
			var props = prop.split(';');
			var strcssText = '';
			for (var i = 0; i < cssTexts.length; i++) {
				var cssTextattrib = cssTexts[i].split(':');
				var usecssTextattrib = 1;
				for (var j = 0; j < props.length; j++) {
					var attrib = props[j].split(':');
					if(attrib[0] == '' || attrib[0] == ' ' || attrib[0] == 'undefined'){
						continue;
					}else if(Trim(attrib[0]) == Trim(cssTextattrib[0])){
						usecssTextattrib = 0;
						break;
					}
				}
				if(usecssTextattrib == 1 && cssTextattrib[0] != '' && cssTextattrib[0] != ' ' && cssTextattrib[0] != 'undefined'){
					strcssText += cssTextattrib[0] + ':' + cssTextattrib[1] + ';';
				}
			}
			el.setAttribute("style", strcssText + prop);
	}
}
function Trim(text){
    return text.replace(/^\s*|\s*$/g,'');
}

function LTrim(text){
    return text.replace(/^\s*/g,'');
}

function RTrim(text){
    return text.replace(/\s*$/g,'');
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
