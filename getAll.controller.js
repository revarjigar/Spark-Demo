
(function() {
    'use strict';

    angular
        .module('plunker')
        .controller('ReservationsController', ReservationsController);

    ReservationsController.$inject = ['reservationService'];

    function ReservationsController (reservationService) {
        var rsvVm = this;
        console.log('ReservationsController');

        init();

        function init() {
            reservationService
                .getReservations()
                .then(function (reservations) {
                    rsvVm.reservations = reservations;
                }, function (error) {
                    console.log(error);
                });
        }
    }
})();
