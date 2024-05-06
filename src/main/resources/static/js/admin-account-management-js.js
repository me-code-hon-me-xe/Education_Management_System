document.addEventListener("DOMContentLoaded", function () {

    var editModal = document.getElementById("edit-modal");
    var addAdminModal = document.getElementById("add-admin-modal");
// Get the button that opens the editModal
    var editBtn = document.getElementById("edit-button");
    var addBtn = document.getElementById("add-admin-button");
    if (editBtn !== null) {
        // Element with the specified ID was found
        console.log("Element found:", editBtn);
    } else {
        // Element with the specified ID was not found
        console.log("Element not found");
    }
    var editSpan = document.getElementById("close-edit");
    var addSpan = document.getElementById("close-add");


// When the user clicks the button, open the editModal
    editBtn.onclick = function () {
        console.log("HEllo")
        editModal.style.display = "block";
    }

    addBtn.onclick = function () {
        addAdminModal.style.display = "block";
    }

// When the user clicks on <editSpan> (x), close the editModal
    editSpan.onclick = function () {
        editModal.style.display = "none";
    }

    addSpan.onclick = function () {
        addAdminModal.style.display = "none";
    }


});