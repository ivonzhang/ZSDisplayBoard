/**
 * Created with JetBrains WebStorm.
 * User: zhangfei
 * Date: 13-8-6
 * Time: 下午7:40
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function(){
    $(".inputNum").change(function(){
        var totalPrice = 0;
        var countNum = 0;
        var array = $(".orderList li");
        for(var i = 0;i < array.length;i++)
        {
            var unitPriceStr = $(".price span").eq(i).text();
            var unitPrice = parseFloat(unitPriceStr);
            var temp = $(".inputNum").eq(i).val();
            var tempCountNum =  parseInt(temp);
            var tempTotalPrice = tempCountNum*unitPrice;
            countNum += tempCountNum;
            totalPrice += tempTotalPrice;
            $("#totalNum").text(countNum);
            $("#totalMoney").text(totalPrice);
        }
    });
});

/*
window.onload = function(){
    document.getElementById("inputNum").onchange = updateData;
}

 function updateData()
{
    var unitPriceStr = $(".price>span").text();
    var unitPrice = parseFloat(unitPriceStr);
    var countNum =  $("#inputNum").val();
    $("#totalNum").text(countNum);
    $("#totalMoney").text(countNum * unitPrice);
}

 $(".inputNum").change(function(){
 var totalPrice = 0;
 var countNum = 0;
 $(".orderList li").each(function(){
 var unitPriceStr = $(".price span").text();
 var unitPrice = parseFloat(unitPriceStr);
 var temp = $(".inputNum").val();
 var tempCountNum =  parseInt(temp);
 var tempTotalPrice = tempCountNum*unitPrice;
 countNum += tempCountNum;
 totalPrice += tempTotalPrice;
 $("#totalNum").text(countNum);
 $("#totalMoney").text(totalPrice);
 });

 });
*/