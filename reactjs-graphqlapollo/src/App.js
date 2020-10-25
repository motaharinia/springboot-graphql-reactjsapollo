import React, {Component} from 'react';
import { BrowserRouter as Router,Switch,Route} from "react-router-dom";

// import ApolloClient from 'apollo-boost';

import { WebSocketLink } from 'apollo-link-ws';
import { HttpLink } from 'apollo-link-http';
import { split } from 'apollo-link';
import { getMainDefinition } from 'apollo-utilities';
import ApolloClient from "apollo-client";
import { InMemoryCache } from 'apollo-cache-inmemory';
import { ApolloProvider } from 'react-apollo';

import AdminList  from "./page/adminUser/AdminList";
import AdminCreate from "./page/adminUser/AdminCreate"
import AdminUpdate from "./page/adminUser/AdminUpdate"
import AdminDelete from "./page/adminUser/AdminDelete"

//لینک ارتباط وب سوکت برای سابسکریپشن
const wsLink = new WebSocketLink({
    uri: `ws://localhost:8082/graphql`,
    options: {
        reconnect: true
    }
});
//لینک ارتباط وب برای کوئری و میوتیشن
const httpLink = new HttpLink({
    uri: `http://localhost:8082/graphql`,
});

// تشخیص نوع عملیات کوئری ها
const splitLink =split(
    ({ query }) => {
        const { kind, operation } = getMainDefinition(query);
        return kind === 'OperationDefinition' && operation === 'subscription';
    },
    wsLink,
    httpLink,
);

// ساخت کلاینت برای ارسال به سرور
const client = new ApolloClient({
    link:splitLink,
    cache: new InMemoryCache()
});

class App extends Component {
    render() {
        return (
            <ApolloProvider client={client } >
                <Router>
                <div>
                    <Switch>
                        <Route path="/adminDelete/:Id">
                            <AdminDelete />
                        </Route>
                        <Route path="/adminUpdate/:Id">
                            <AdminUpdate />
                        </Route>
                        <Route path="/adminCreate">
                            <AdminCreate />
                        </Route>
                        <Route path="/">
                            <AdminList />
                        </Route>
                    </Switch>
                </div>
                </Router>
            </ApolloProvider>
        );
    }
}

export default App;