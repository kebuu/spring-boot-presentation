var module = angular.module('springBootRaceServer', []);

module.controller('mainCtrl', function($scope, $http, $interval) {
    /**
        {
            pseudo: "pseudo",
            stepInfos: [{
                step: "_1",
                instant: "2014-10-01 14:23:44"
            }]
        }
    */

    $scope.gameStatus = [];

    $interval(function() {
        $http.get('/gameStatus').success(function(data) {
            console.log(data.userGameStatus, arguments);
            $scope.gameStatus = data.userGameStatus;
        })
    }, 1000);

});