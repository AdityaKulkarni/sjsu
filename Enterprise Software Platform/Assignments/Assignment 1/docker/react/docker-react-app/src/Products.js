import { useEffect, useState } from "react"
const Products = () => {
    const [products, setProducts] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        getProducts();
    }, [])

    const getProducts = () => {
        fetch("https://dummyjson.com/products?limit=100").then(response => {
            response.json().then(resJson => {
                console.log("data: " + JSON.stringify(resJson.products))
                setProducts(resJson.products)
            })
        }).catch(e => {
            setError(e.message)
        })
    }

    return <div data-testid="products" style={{minHeight: "100%", minWidth: "100%"}}>
        {products.length > 0 && <>
            {products.map(product => <div>{product.title}</div>)}
        </>}
    </div>
}

export default Products