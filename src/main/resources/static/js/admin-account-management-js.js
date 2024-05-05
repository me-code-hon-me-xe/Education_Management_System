      document.addEventListener("DOMContentLoaded", function () {
        var editModal = document.getElementById("edit-modal");
        var addModal = document.getElementById("add-modal");
        // Get the button that opens the editModal
        var addBtn = document.getElementById("add-button");
        if (editModal !== null) {
          // Element with the specified ID was found
          console.log("Element found:", editModal);
          console.log(addModal);
        } else {
          // Element with the specified ID was not found
          console.log("Element not found");
        }
        var editSpan = document.getElementById("close-edit");
        var addSpan = document.getElementById("close-add");

        // When the user clicks the button, open the editModal


        addBtn.onclick = function () {
          addModal.style.display = "block";
        };

        // When the user clicks on <editSpan> (x), close the editModal
        editSpan.onclick = function () {
          editModal.style.display = "none";
        };

        addSpan.onclick = function () {
          addModal.style.display = "none";
        };
        var editModal = document.getElementById("edit-modal");
        var addModal = document.getElementById("add-modal");

        // Get the cancel buttons
        var cancelAddBtn = document.getElementById("cancel-add");
        var cancelEditBtn = document.getElementById("cancel-edit");

        // When the user clicks the cancel button in the "Add User" modal
        cancelAddBtn.onclick = function () {
          addModal.style.display = "none"; // Close the "Add User" modal
        };

        // When the user clicks the cancel button in the "Edit User" modal
        cancelEditBtn.onclick = function () {
          editModal.style.display = "none"; // Close the "Edit User" modal
        };
      });