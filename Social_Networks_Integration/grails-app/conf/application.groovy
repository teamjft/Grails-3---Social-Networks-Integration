

// Added by the Spring Security Core plugin:
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.jft.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.jft.security.UserRole'
grails.plugin.springsecurity.authority.className = 'com.jft.security.Role'
grails.plugin.springsecurity.successHandler.alwaysUseDefault = true
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/dashboard/home'
grails.plugin.springsecurity.logoutHandler.defaultTargetUrl = '/dashboard/index'


grails.plugin.springsecurity.controllerAnnotations.staticRules = [
		[pattern: '/',               access: ['permitAll']],
		[pattern: '/error',          access: ['permitAll']],
		[pattern: '/index',          access: ['permitAll']],
		[pattern: '/index.gsp',      access: ['permitAll']],
		[pattern: '/shutdown',       access: ['permitAll']],
		[pattern: '/assets/**',      access: ['permitAll']],
		[pattern: '/**/js/**',       access: ['permitAll']],
		[pattern: '/**/css/**',      access: ['permitAll']],
		[pattern: '/**/images/**',   access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']],
		[pattern: '/register/**', 	 access: ['IS_AUTHENTICATED_ANONYMOUSLY']],
		[pattern: '/dbconsole/**', 	 access: ['IS_AUTHENTICATED_ANONYMOUSLY']],
		[pattern: '/dashboard/**', 	 access: ['IS_AUTHENTICATED_FULLY']],
]


grails.plugin.springsecurity.filterChain.chainMap = [
		[pattern: '/assets/**',      filters: 'none'],
		[pattern: '/**/js/**',       filters: 'none'],
		[pattern: '/**/css/**',      filters: 'none'],
		[pattern: '/**/images/**',   filters: 'none'],
		[pattern: '/**/favicon.ico', filters: 'none'],
		[pattern: '/**',             filters: 'JOINED_FILTERS']
]

