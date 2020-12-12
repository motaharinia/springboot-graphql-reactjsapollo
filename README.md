## Spring Boot with GraphQL and ReactJS-Apollo

### GraphQL (queries, mutations, subscriptions):
GraphQL provides a complete and understandable description of the data in your API, gives clients the power to ask for exactly what they need and nothing more, makes it easier to evolve APIs over time, and enables powerful developer tools.
- query:    
the structure of GraphQL queries makes it possible to uphold the common saying that, “With GraphQL, you can ask for what you want and get exactly that in return, no more, no
- mutation:     
In GraphQL, mutations are mostly used to modify data on the server.
- subscription:     
According to the GraphQL documentation, subscriptions are a way to push data from the server to the clients that choose to listen to real-time messages from the server.

### Spring Boot with GraphQL-SPQR:
GraphQL SPQR aims to make it dead simple to add a GraphQL API to any Java project. It works by dynamically generating a GraphQL schema from Java code.
- Requires minimal setup (~3 lines of code)
- Deeply configurable and extensible (not opinionated)
- Allows rapid prototyping and iteration (no boilerplate)
- Easily used in legacy projects with no changes to the existing code base
- Has very few dependencies

### ReactJS with GraphQL-Apollo:
1. GraphQl libraries:
    - Apollo-Client [https://www.apollographql.com]
    - Relay [https://relay.dev/en/]
    - GraphQL-Request [https://github.com/prisma-labs/graphql-request]
    - GraphQL-CLI [https://github.com/Urigo/graphql-cli]
    - GraphQL-Compose [https://github.com/graphql-compose/graphql-compose]
    - @octokit [https://github.com/octokit/graphql.js/]
2. Apollo is the industry-standard GraphQL implementation, providing the data graph layer that connects modern apps to the cloud.
Appolo-Client features:
    - Declarative data fetching: Write a query and receive data without manually tracking loading states.
    - Excellent developer experience: Enjoy helpful tooling for TypeScript, Chrome DevTools, and VS Code.
    - Designed for modern React: Take advantage of the latest React features, such as hooks.
    - Incrementally adoptable: Drop Apollo into any JavaScript app seamlessly.
    - Universally compatible: Use any build setup and any GraphQL API.
    - Community driven: Share knowledge with thousands of developers, thanks to our active open source community.

further references:    
- https://graphql.org
- https://github.com/leangen/graphql-spqr
- https://www.apollographql.com

### Project Descriptions:
1. Server-side:     
please see application.properties files in resources folder and select a active profile "dev" or "com" to run project. you can check test methods too.  

2. Client-side:     
This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).    
install dependencies:   
```cd reactjs-graphqlapollo```  
```yarn global add react react-dom```   
```yarn global add typescript @types/node @types/react @types/react-dom @types/jest```  
```yarn global add create-react-app```  
```yarn add apollo-client apollo-link-ws apollo-link-http apollo-link apollo-utilities apollo-cache-inmemory react-apollo graphql```    
```yarn start```   

### IntellliJ IDEA Configurations:
- IntelijIDEA: Help -> Edit Custom Vm Options -> add these two line:
    - -Dfile.encoding=UTF-8
    - -Dconsole.encoding=UTF-8
- IntelijIDEA: File -> Settings -> Editor -> File Encodings-> Project Encoding: form "System default" to UTF-8. May be it affected somehow.
- IntelijIDEA: File -> Settings -> Editor -> General -> Code Completion -> check "show the documentation popup in 500 ms"
- IntelijIDEA: File -> Settings -> Editor -> General -> Auto Import -> check "Optimize imports on the fly (for current project)"
- IntelijIDEA: File -> Settings -> Editor -> Color Scheme -> Color Scheme Font -> Scheme: Default -> uncheck "Show only monospaced fonts" and set font to "Tahoma"
- IntelijIDEA: Run -> Edit Configuration -> Spring Boot -> XXXApplication -> Configuration -> Environment -> VM Options: -Dspring.profiles.active=dev
- IntelijIDEA: Run -> Edit Configuration -> Spring Boot -> XXXApplication -> Code Coverage -> Fix the package in include box

<hr/>
<a href="mailto:eng.motahari@gmail.com?"><img src="https://img.shields.io/badge/gmail-%23DD0031.svg?&style=for-the-badge&logo=gmail&logoColor=white"/></a>


