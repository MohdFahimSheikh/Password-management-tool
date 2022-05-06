
function deleteAccount(id){
    swal({
      title: "Are you sure?",
      text: "This action cannot be undone!",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        window.location="/accounts/deleteAccount/"+id;
        swal("Account Deleted!", {
              icon: "success",
            });
      } else {

      }
    });
}


function viewPassword(password){
    console.log("mai aa raha hu");
    swal({
        title: "Here is your Password",
        text: password,
        icon: "warning",
        buttons: true,
        dangerMode: true,
    });
}

