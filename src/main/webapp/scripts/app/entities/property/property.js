'use strict';

angular.module('cumulusApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('property', {
                parent: 'entity',
                url: '/property',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.property.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/property/propertys.html',
                        controller: 'PropertyController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('property');
                        return $translate.refresh();
                    }]
                }
            })
            .state('propertyDetail', {
                parent: 'entity',
                url: '/property/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.property.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/property/property-detail.html',
                        controller: 'PropertyDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('property');
                        return $translate.refresh();
                    }]
                }
            });
    });
