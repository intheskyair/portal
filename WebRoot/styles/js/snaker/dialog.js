function selectOrg(ctx, e1, e2)
{
	var element1 = document.getElementById(e1);
	var element2 = document.getElementById(e2);
 	var l  = window.showModalDialog(ctx + "/dialogs/selectDialog.jsp?type=orgTree"," ","dialogWidth:800px;dialogHeight:540px;center:yes;scrolling:yes");
 	if (l == null )
 	    return;
 	var result = splitUsersAndAccounts(l);
 	element1.value = result[0];
 	element1.title = result[0];
 	element2.value = result[1];
}

function selectOrgUser(ctx, e1, e2)
{
	var element1 = document.getElementById(e1);
	var element2 = document.getElementById(e2);
 	var l  = window.showModalDialog(ctx + "/dialogs/selectDialog.jsp?type=orgUserTree"," ","dialogWidth:800px;dialogHeight:540px;center:yes;scrolling:yes");
 	if (l == null )
 	    return;
 	var result = splitUsersAndAccounts(l);
 	element1.value = result[0];
 	element1.title = result[0];
 	element2.value = result[1];
}

function splitUsersAndAccounts( userNamesAndAccount )
{
	var userNames = "";
	var accounts = "";

	var array = userNamesAndAccount.split( ";" );
	for(i=0; i<array.length; i++)
	{
		var temp = splitUserNameAndAccount(array[i]);
		userNames += temp[0] + ",";
		accounts += temp[1] + ",";
	}
	var result = new Array(2);
	result[0] = userNames;
	result[1] = accounts;
	return result;
}

function splitUserNameAndAccount( userNameAndAccount )
{
	var temp = new Array(2);
	if(userNameAndAccount.indexOf( "(" ) != -1)
	{
		temp[0] = userNameAndAccount.substring( 0,
      	userNameAndAccount.indexOf( "(" ) );
    	temp[1] = userNameAndAccount.substring( userNameAndAccount.indexOf( "(" ) + 1,
        userNameAndAccount.indexOf( ")" ) );
    }
    else
    {
    	temp[0] = userNameAndAccount;
    	temp[1] = userNameAndAccount;
    }
    return temp;
}


/**
	选择部门
	num:0是转派，1是抄送.
*/
var selectPart = function(str,callback,type,addr){
	if('undefined' == typeof(str) || $.trim(str).length == 0){
		alert("对象不存在")
		return;
	}
	callback.call(this,type);
	//$("#"+ str).modal({show:true,backdrop:false});
	var select;
	if('undefined' == typeof(addr) || $.trim(addr).length == 0){
		select = $("#select-body");
	}
	else
	{
		select = $("#"+addr);
	}
	  var dlgParent = select.parent();
      var dlgOwn = select.clone();
	 var depart = select.dialog({
		 position: ['right','top'],
			draggable :true,     
			autoOpen: false,
			height:600,
			width:360,
			show: "slde",
			modal:true,
			cache:false,
			title:'选择人员',
			buttons:{
				"确定":function(){
					$(this).dialog("close");
				}
			},
			onClose:function()
			{  
                //$(this).dialog('destroy');  
            	dlgOwn.appendTo(dlgParent);
            	$(this).dialog("destroy").remove();
            }  
		});
	
	 depart.dialog("open");
}
/**
 * 关闭dialog
 */
var closeDialog = function(str){
	if('undefined' == typeof(str) || $.trim(str).length == 0){
		alert("对象不存在")
		return;
	}
	$("#serch_text").val("");
	$("#citySelOrgId").val("");
	$("#select-body").dialog("destroy").close();
}
