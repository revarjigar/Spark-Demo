
(function() {
    'use strict';

    angular
        .module('plunker')
        .service('reservationService', reservationService);

    reservationService.$inject = ['$q', '$http'];

    function reservationService($q, $http) {
        var self = this;

        self.getReservations = getReservations;
        self.getReservationById = getReservationById;

        //private members
        function getReservations() {

            var defer = $q.defer();

            $http
                .get('http://localhost:8080/RESTDB/api/rsv')
                .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;
        }

        function getReservationById(id) {
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/RESTDB/api/rsv/' + id)
                .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;
        }
    }
})();