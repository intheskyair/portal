/*  
===============================================================================
WResize is the jQuery plugin for fixing the IE window resize bug
...............................................................................
                                               Copyright 2007 / Andrea Ercolino
-------------------------------------------------------------------------------
LICENSE: http://www.opensource.org/licenses/mit-license.php
WEBSITE: http://noteslog.com/
===============================================================================


* <p>Copyright: ©2011 tohot Co., Ltd. All rights reserved</p>
* <p>Title: jQuery重定位大小插件</p> 
* <p>name: wResize</p> 
* <p>Description: 处理窗口大小变更时触发的事件</p>
*/ 

( function( $ ) 
{
	$.fn.wresize = function( f ) 
	{
		version = '1.1';
		wresize = {fired: false, width: 0};

		function resizeOnce() 
		{
			if ( $.browser.msie )
			{
				if ( ! wresize.fired )
				{
					wresize.fired = true;
				}
				else 
				{
					var version = parseInt( $.browser.version, 10 );
					wresize.fired = false;
					if ( version < 7 )
					{
						return false;
					}
					else if ( version == 7 )
					{
						//a vertical resize is fired once, an horizontal resize twice
						var width = $( window ).width();
						if ( width != wresize.width )
						{
							wresize.width = width;
							return false;
						}
					}
				}
			}

			return true;
		}

		function handleWResize( e ) 
		{
			if ( resizeOnce() )
			{
				return f.apply(this, [e]);
			}
		}

		this.each( function() 
		{
			if ( this == window )
			{
				$( this ).resize( handleWResize );
			}
			else
			{
				$( this ).resize( f );
			}
		} );

		return this;
	};

} ) ( jQuery );
