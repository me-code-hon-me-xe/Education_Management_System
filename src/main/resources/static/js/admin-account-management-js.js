document.addEventListener("DOMContentLoaded", function () {

    var editModal = document.getElementById("edit-modal");
    var addAdminModal = document.getElementById("add-modal");
// Get the button that opens the editModal
    var editBtn = document.getElementById("edit-button");
    var addBtn = document.getElementById("add-button");
    var addAdminCancel = document.getElementById("cancel-add")


// When the user clicks the button, open the editModal
    if (editBtn) {
        editBtn.onclick = function () {
            console.log("HEllo")
            editModal.style.display = "block";
        }
    }

    addBtn.onclick = function () {
        addAdminModal.style.display = "block";
    }

// When the user clicks on <editSpan> (x), close the editModal
    addAdminCancel.onclick = function () {
        addAdminModal.style.display = "none"
    }


});