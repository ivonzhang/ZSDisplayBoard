/**
 * Created with JetBrains WebStorm.
 * User: zhangfei
 * Date: 13-8-7
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
$(function(){
    $("#newAddressForm").validate(
        {
            rules:{//自定义规则
                username:{required:true},
                address:{required:true},
                mobile:{required:true},
                postcode:{required:true}
            },

            //错误提示位置
            errorPlacement: function(error,element){
                error.appendTo(element.siblings("span"));
            }
        }
    );
});