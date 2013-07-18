function isExist(option,list){
    for(j = 0; j < list.length; j++) {
        if ((list.options[i] != null) && (option.value==list.options[i].value)){
            return true;
        }
    }
    return false;
}
function addSrcToDestList(src,des) {
    for(i = 0; i < src.length; i++) {
        if ((src.options[i] != null) && (src.options[i].selected)) {
            if(!isExist(src.options[i],des)){
                des.options[des.length] = new Option(src.options[i].text,src.options[i].value); 
            }
        }
    }
    for(i=src.length;i>=0;i--){
        if ((src.options[i] != null) && (src.options[i].selected)) {
            src.options[i] = null;
        }
    }
}
