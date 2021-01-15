
function minLengthcardNo() {
	try{

		if(document.getElementById("cardno")!=null && (document.getElementById("cardno").value.length<15)){
			document.getElementById("lengthValidation").innerHTML="card number should be atleast 15 digits.";
		}
		else{
			document.getElementById("lengthValidation").innerHTML="";
		}
	}catch(err){
		document.getElementById("errordiv").innerHTML=err;
	}



}

function ChooseCurrency(country){
	if(country =="UK"){
		document.getElementById("currency").innerHTML="Amount (UGMP)";
	}
	else{
		document.getElementById("currency").innerHTML="Amount (USD)";
	}

}

function changePayValue(amount){
	try{
		if(amount.value==""){
			document.getElementById("amountValidation").innerHTML="amount cannot be zero.";
		}
		else{
			var value = parseFloat(amount.value);
			if(value<=0){
				document.getElementById("amountValidation").innerHTML="amount cannot be zero.";
			}
			else{

				document.getElementById("pay").innerHTML="Pay "+value;
			}
		}
	}catch(err){
		document.getElementById("errordiv").innerHTML=err;
	}


}

function isAmountKey(evt)
{
     var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 46 && charCode > 31
            && (charCode < 48 || charCode > 57))
        return false;

		return true;
	}

function onFormSubmit(){

	 var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {

        document.getElementById("loader").style.display = "block";
            document.getElementById("formdiv").style.display = "none";
            document.getElementById("footer").style.display = "none";
        if (this.readyState == 4 && this.status == 200) {
          document.getElementById("successDiv").style.display = "block";
          document.getElementById("successDiv").style.display = "block";
          var res=JSON.parse(this.response);
          document.getElementById("successText").innerHTML="<h1 class=\"display-3\">Thank You!</h1><br>Your payment for <strong>"
                                                                +res.amount+" "+res.country+"</strong> was Successful!<br>Transaction Id:AWB"
                                                                +res.id+"<br>Transaction Time: "
                                                                +new Date(res.transactionTime);
          document.getElementById("loader").style.display = "none";
        }
      };
      xhttp.open("POST", "/api/saveTransaction", true);
      xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xhttp.send(
      "cardNumber="+document.getElementById("cardno").value+
                              "&cvv="+document.getElementById("cvv").value+
                               "&expDate="+document.getElementById("expdate").value+
                               "&mobile="+document.getElementById("phone").value+
                               "&email="+document.getElementById("emailId").value+
                               "&address="+document.getElementById("address").value+","+document.getElementById("city").value+","+
                               document.getElementById("state").value+","+document.getElementById("pcode").value+
                               "&country="+document.getElementById("country").value+
                               "&amount="+document.getElementById("amount").value+
                               "&cusName="+document.getElementById("cusName").value);
}




 var timeout;

function myLoader() {
  timeout = setTimeout(showPage, 3000);
  transactionTimeStamp();}

function showPage() {
  document.getElementById("loader").style.display = "none";
  document.getElementById("successDiv").style.display = "block";
}
