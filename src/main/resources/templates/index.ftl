<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 40px 0;
        }

        .main-container {
            background: white;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
            padding: 40px;
        }

        .page-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            border-radius: 15px;
            margin-bottom: 30px;
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .page-header h1 {
            margin: 0;
            font-weight: 700;
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .page-header p {
            margin: 10px 0 0 0;
            opacity: 0.95;
        }

        .table-container {
            background: #f8f9fa;
            border-radius: 15px;
            padding: 20px;
        }

        .custom-table {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .custom-table thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .custom-table thead th {
            padding: 15px;
            font-weight: 600;
            border: none;
        }

        .custom-table tbody td {
            padding: 15px;
            vertical-align: middle;
        }

        .custom-table tbody tr:hover {
            background-color: #f1f3ff;
            transition: background-color 0.3s ease;
        }

        .btn-action {
            padding: 8px 15px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 500;
            transition: all 0.3s ease;
            border: none;
            margin: 0 3px;
        }

        .btn-stock {
            background: linear-gradient(135deg, #3498db, #2980b9);
            color: white;
        }

        .btn-stock:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(52, 152, 219, 0.4);
        }

        .btn-price {
            background: linear-gradient(135deg, #f39c12, #e67e22);
            color: white;
        }

        .btn-price:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(243, 156, 18, 0.4);
        }

        .btn-delete {
            background: linear-gradient(135deg, #e74c3c, #c0392b);
            color: white;
        }

        .btn-delete:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(231, 76, 60, 0.4);
        }

        .btn-add {
            background: linear-gradient(135deg, #2ecc71, #27ae60);
            color: white;
            padding: 12px 30px;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
            border: none;
            transition: all 0.3s ease;
        }

        .btn-add:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(46, 204, 113, 0.4);
        }

        .badge-stock {
            background: linear-gradient(135deg, #3498db, #2980b9);
            padding: 8px 15px;
            border-radius: 20px;
            font-weight: 600;
        }

        .badge-price {
            background: linear-gradient(135deg, #27ae60, #229954);
            padding: 8px 15px;
            border-radius: 20px;
            font-weight: 600;
            color: white;
        }

        .empty-state {
            text-align: center;
            padding: 60px 20px;
            color: #6c757d;
        }

        .empty-state i {
            font-size: 80px;
            margin-bottom: 20px;
            opacity: 0.5;
        }

        .footer {
            text-align: center;
            margin-top: 30px;
            color: white;
            opacity: 0.9;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="main-container">
        <div class="page-header">
            <h1>
                <i class="bi bi-box-seam"></i>
                Inventory Management System
            </h1>
            <p>Manage your products with Kafka & MongoDB integration</p>
        </div>

        <div class="d-flex justify-content-between align-items-center mb-3">
            <h4 class="mb-0"><i class="bi bi-list-ul"></i> Product Catalog</h4>
            <a href="create" class="btn btn-add">
                <i class="bi bi-plus-circle"></i> Add New Product
            </a>
        </div>

        <div class="table-container">
            <#if inventories?? && (inventories?size > 0)>
                <div class="table-responsive">
                    <table class="table custom-table mb-0">
                        <thead>
                        <tr>
                            <th><i class="bi bi-tag"></i> Product Name</th>
                            <th><i class="bi bi-box"></i> Stock</th>
                            <th><i class="bi bi-currency-dollar"></i> Price</th>
                            <th class="text-center"><i class="bi bi-gear"></i> Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list inventories as inventory>
                            <tr>
                                <td>
                                    <strong>${inventory.productName}</strong>
                                </td>
                                <td>
                                    <span class="badge badge-stock">${inventory.stock} units</span>
                                </td>
                                <td>
                                    <span class="badge badge-price">$${inventory.price?string["0.00"]}</span>
                                </td>
                                <td class="text-center">
                                    <a href="editStock/${inventory.id}" class="btn btn-action btn-stock"
                                       title="Update Stock">
                                        <i class="bi bi-box-seam"></i> Stock
                                    </a>
                                    <a href="editPrice/${inventory.id}" class="btn btn-action btn-price"
                                       title="Update Price">
                                        <i class="bi bi-cash-coin"></i> Price
                                    </a>
                                    <a href="delete/${inventory.id}" class="btn btn-action btn-delete"
                                       onclick="return confirm('Are you sure you want to delete ${inventory.productName}?')"
                                       title="Delete Product">
                                        <i class="bi bi-trash"></i> Delete
                                    </a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <#else>
                <div class="empty-state">
                    <i class="bi bi-inbox"></i>
                    <h4>No Products Yet</h4>
                    <p>Start by adding your first product to the inventory</p>
                    <a href="create" class="btn btn-add mt-3">
                        <i class="bi bi-plus-circle"></i> Add Your First Product
                    </a>
                </div>
            </#if>
        </div>
    </div>

    <div class="footer">
        <p><i class="bi bi-code-slash"></i> Built with Spring Boot, Kafka & MongoDB</p>
        <p>&copy; 2025 Inventory System. All rights reserved.</p>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
