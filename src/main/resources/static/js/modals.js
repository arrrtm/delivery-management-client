$(document).ready(function () {
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

    $('.table .detailOrder, .table .editOrder, .table .deleteOrder, .table .destroyOrder, .table .qrOrder, .table .sentOrder').on('click', function (event) {
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
            $('.customForm #userFullNameAO').val(order.users[0].userFullName);
            $('.customForm #userPhoneNumberAO').val(order.users[0].userPhoneNumber);
            $('.customForm #emailAO').val(order.users[0].email);
        });
    });
});