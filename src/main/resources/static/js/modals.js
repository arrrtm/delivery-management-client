$(document).ready(function () {
    $('.table .editUser, .table .deleteUser').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

        $.get(href, function (user) {
            $('.customForm #id').val(user.id);
            $('.customForm #username').val(user.username);
            $('.customForm #userFullName').val(user.userFullName);
            $('.customForm #userPhoneNumber').val(user.userPhoneNumber);
            $('.customForm #email').val(user.email);
            $('.customForm #role').val(user.role.id);
            $('.customForm #branches').val(user.branches[0].id);
        });
    });

    $('.table .editOrder, .table .deleteOrder').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

        $.get(href, function (order) {
            $('.customForm #id').val(order.id);
            $('.customForm #addressPickup').val(order.addressPickup);
            $('.customForm #addressDelivery').val(order.addressDelivery);
            $('.customForm #branch').val(order.branch.id);
            $('.customForm #card').val(order.card.id);
            $('.customForm #client').val(order.client.id);
        });
    });

    $('.table .editBranch, .table .deleteBranch').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

        $.get(href, function (branch) {
            $('.customForm #id').val(branch.id);
            $('.customForm #name').val(branch.name);
            $('.customForm #address').val(branch.address);
        });
    });

    $('.table .editClient, .table .deleteClient').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

        $.get(href, function (client) {
            $('.customForm #id').val(client.id);
            $('.customForm #clientPin').val(client.clientPin);
            $('.customForm #clientFullName').val(client.clientFullName);
            $('.customForm #clientPhoneNumber').val(client.clientPhoneNumber);
        });
    });

    $('.table .detailOrder, .table .destroyOrder, .table .sentOrder').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

        $.get(href, function (order) {
            $('.customForm #id, #idNO, #idAO').val(order.id);
            $('.customForm #addressPickup, #addressPickupNO, #addressPickupAO').val(order.addressPickup);
            $('.customForm #addressDelivery, #addressDeliveryNO, #addressDeliveryAO').val(order.addressDelivery);
            $('.customForm #typeDelivery, #typeDeliveryNO, #typeDeliveryAO').val(order.typeDelivery);
            $('.customForm #status, #statusNO, #statusAO').val(order.status);
            $('.customForm #createdDate, #createdDateNO, #createdDateAO').val(order.createdDate.replaceAll("T", " "));
            $('.customForm #updatedDate, #updatedDateNO, #updatedDateAO').val(order.updatedDate.replaceAll("T", " "));
            $('.customForm #typeCard, #typeCardNO, #typeCardAO').val(order.card.typeCard);
            $('.customForm #description, #descriptionNO, #descriptionAO').val(order.card.description);
            $('.customForm #clientPin, #clientPinNO, #clientPinAO').val(order.client.clientPin);
            $('.customForm #clientFullName, #clientFullNameNO, #clientFullNameAO').val(order.client.clientFullName);
            $('.customForm #clientPhoneNumber, #clientPhoneNumberNO, #clientPhoneNumberAO').val(order.client.clientPhoneNumber);
            $('.customForm #branchName, #branchNameNO, #branchNameAO').val(order.branch.name);
            $('.customForm #branchAddress, #branchAddressNO, #branchAddressAO').val(order.branch.address);

            if (!(order.users.length === 0)) {
                $('.customForm #userFullNameAO').val(order.users[0].userFullName);
                $('.customForm #userPhoneNumberAO').val(order.users[0].userPhoneNumber);
                $('.customForm #emailAO').val(order.users[0].email);
            }
        });
    });

    $('.table .detailStory').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

        $.get(href, function (order) {
            $('.customForm #id').val(order.id);
            $('.customForm #addressPickup').val(order.addressPickup);
            $('.customForm #addressDelivery').val(order.addressDelivery);
            $('.customForm #orderNumber').val(order.orderNumber);
            $('.customForm #comment').val(order.comment);
            $('.customForm #status').val(order.status);
            $('.customForm #createdDate').val(order.createdDate.replaceAll("T", " "));
            $('.customForm #updatedDate').val(order.updatedDate.replaceAll("T", " "));
            $('.customForm #typeCard, #typeCardNO, #typeCardAO').val(order.card.typeCard);
            $('.customForm #description, #descriptionNO, #descriptionAO').val(order.card.description);
            $('.customForm #clientPin').val(order.client.clientPin);
            $('.customForm #clientFullName').val(order.client.clientFullName);
            $('.customForm #clientPhoneNumber, #clientPhoneNumberNO, #clientPhoneNumberAO').val(order.client.clientPhoneNumber);
            $('.customForm #branchName').val(order.branch.name);
            $('.customForm #branchAddress').val(order.branch.address);
            $('.customForm #userFullName').val(order.user.userFullName);
            $('.customForm #userPhoneNumber').val(order.user.userPhoneNumber);
            $('.customForm #email, #emailAO').val(order.user.email);
        });
    });
});