var module = angular.module('springBootRaceServer', []);

module.controller('mainCtrl', function($scope, $http, $interval) {

    $scope.gameStatus = [];

    $interval(function() {
        $http.get('/gameStatus').success(function(data) {

            _.forEach(data.userGameStatus, function(userGame) {
                userGame.nbOfValidatedStep = userGame.stepInfos.length;

                userGame.validatedSteps = _.chain(userGame.stepInfos)
                                              .map(function(stepInfo) {
                                                  return stepInfo.step.replace('_', '');
                                              })
                                              .sortBy()
                                              .value()
                                              .join();

                userGame.chrono = _.chain(userGame.stepInfos)
                                    .map(function(stepInfo) {
                                        return moment(stepInfo.instant.epochSecond * 1000).toDate();
                                    })
                                    .max()
                                    .value();

                userGame.chronoString = moment(userGame.chrono).format('hh:mm:ss DD/MM/YYYY');
            });

            $scope.gameStatus = data.userGameStatus;
        })
    }, 1000);

});