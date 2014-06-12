var module = angular.module('springBootRaceClient', []);

module.controller('mainCtrl', function($scope) {

    $scope.startPlaying = function() {
        console.log($scope.configForm, $scope.pseudo);
    }
});