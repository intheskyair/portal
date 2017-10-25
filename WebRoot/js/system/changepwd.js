	var ok1=false;  	
	var ok2=false;  	
	var ok3=false; 
$(function()
{  
 	 
   //验证原密码  	
	$('input[id="pwd1"]').focus(function()
	{  	
	    $(this).next().text('原密码6-20位之间').removeClass('state1').addClass('state2');  
	
	}
	).blur(function(){  
		if($(this).val().length >= 6 && $(this).val().length <=20 && $(this).val()!='')
		{  
			//$(this).next().text('输入成功').removeClass('state1').addClass('state4');
			//alert($(this).val());
			DwrService.userLogin($(this).val(),function(res)
				{	
					if(res)
					{
						//alert(res);
						$('input[id="pwd1"]').next().text('原密码正确').removeClass('state1').addClass('state4');
						ok1=true;
					}
					else
					{
						
			            $('input[id="pwd1"]').next().text('原密码不正确').removeClass('state1').addClass('state3');
						
					}
					//return;
			
				});
			  
	    }
		else
	    {  
	
	     $(this).next().text('原密码6-20位之间，且不能为空').removeClass('state1').addClass('state3');  
	
	    }  
	
     });  
		
	   //验证新密码  	
	$('input[id="pwd2"]').focus(function()
	{  	
	    $(this).next().text('新密码6-20位之间').removeClass('state1').addClass('state2');  
	
	}
	).blur(function(){  
		if($(this).val().length >= 6 && $(this).val().length <=20 && $(this).val()!='')
		{  
			$(this).next().text('输入成功').removeClass('state1').addClass('state4');  	
			ok2=true;  
	    }else
	    {  
	
	     $(this).next().text('新密码6-20位之间，且不能为空').removeClass('state1').addClass('state3');  
	
	    }  
	
     });  
	
    //验证确认密码  	
	$('input[id="pwd3"]').focus(function()
	{  	
	    $(this).next().text('确认密码6-20位之间').removeClass('state1').addClass('state2');  
	
	}
	).blur(function(){  
		if($(this).val().length >= 6 && $(this).val().length <=20 && $(this).val()!='')
		{  
			if($(this).val() == $('input[id="pwd2"]').val())
			{
			   $(this).next().text('输入正确').removeClass('state1').addClass('state4');  
			   ok3=true;
			}
			else
			{
			   $(this).next().text('新密码和确认密码不一致').removeClass('state1').addClass('state3');
			}
	    }
		else
	    {  
	
	     $(this).next().text('新密码6-20位之间，且不能为空').removeClass('state1').addClass('state3');  
	
	    }  
	
     });  
	


	
});

   $('input[id="sub"]').click( function()
   {
        if(ok1 && ok2 && ok3)
        {  
           DwrService.changepwd($('input[id="pwd1"]').val(),$('input[id="pwd2"]').val(),bak_changepwd); 
        }
        else
        {  
        	alert("请输入正确的密码信息！");      	
            return false;  
        }  
   });

	function bak_changepwd(res)
	{
			if(res)
			{
				alert("恭喜你密码修改成功！"); 
				window.location.href="queryUser!changepwd.action";
			}
            else
			{
				alert("对不起，密码修改失败！");
			}
			return;
     }


