document.addEventListener("DOMContentLoaded", function () {

    var editModal = document.getElementById("edit-modal");
    var addAdminModal = document.getElementById("add-modal");
// Get the button that opens the editModal
    var editBtn = document.getElementById("edit-button");
    var addBtn = document.getElementById("add-button");
    var addAdminCancel = document.getElementById("cancel-add")
    addBtn.onclick = function () {
        addAdminModal.style.display = "block";
    }
    // const errorMessageElement = document.querySelectorAll(".error-message");
    // let errorMessage;
    // // Check if the error message is present
    // errorMessage = errorMessageElement.textContent.trim();
    // console.log(errorMessage);
    // // If there is an error message or the errorMessage variable is not empty, show the modal
    // if (errorMessage) {
    //     addAdminModal.style.display = "block";
    // }
// When the user clicks the button, open the editModal
    if (editBtn) {
        editBtn.onclick = function () {
            console.log("Hello")
            editModal.style.display = "block";
        }
    }



// When the user clicks on <editSpan> (x), close the editModal
    addAdminCancel.onclick = function () {
        addAdminModal.style.display = "none"
    }


});