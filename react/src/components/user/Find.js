import React, { useEffect, useState } from "react";

import server from "../common/server";

const Find = () => {
    const [jspElement, setJspElement] = useState(null);

    const createMarkup = (data) => {
        return { __html: data };
    };

    useEffect(() => {
        fetch(server + "/find/form")
            .then((res) => {
                return res.text();
            })
            .then((html) => {
                setJspElement(html);
            });
    }, []);

    return <div dangerouslySetInnerHTML={{ __html: jspElement }} />;
};

export default Find;
