/**
 * 常规校验函数
 */
 /**
  * 检查控件值 是否只包含字幕数字下划线 即不包含特殊符号
  */
function checkNoSign(val){	
	var reg=/^\w+$/;
	return reg.exec(val);
}
/**
 * 检查控件值 是否只包含数字
 * @param {} val
 */
function checkNumber(val){
	var reg=/^\d+$/;
	return reg.exec(val);
}
